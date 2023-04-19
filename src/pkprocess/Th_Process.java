package pkprocess;


import java.util.concurrent.TimeUnit;

public class Th_Process extends Thread {
    private boolean terminated = false;
    private static final int CYCLE_PROCESS = 10;
    private final Lancement lancement;
    private StrucEntrees strucEntrees;
    private StrucSorties strucSorties;
    private long tempsDePause;

    private long tempsCourrant;

    private boolean tempsregle = false;


    /**
     * est un sémaphore utilisé pour afficher qu'une fois les messages de saisie (ça evite de spammer) des messages
     */
    private boolean semaphore_MSG = false;

    /**
     * est un sémaphore utilisé pour afficher qu'une fois les messages de saisie (ça evite de spammer) les valeurs analogique inutiles
     */
    private boolean semaphore_Valeur_Analogique = false;


    /**
     * permet de stocker la valeur du temps pour l'équivalent du Thread.Sleep non bloquant
     * cela permet de lire les entrées réalisé par l'utilisateur.
     */
    private long tempo;

    /**
     * compteur utilisé avec la tempo
     */
    private int cpt;

    /**
     * entier qui stock la valeur de la dernière étape avant le défaut usine
     */
    private int svg_etape;

    public Th_Process(Lancement lancement) {
        this.lancement = lancement;
    }

    public void finish() {
        terminated = true;
    }

    /**
     * méthode bloquante qui permet d'attendre temps ms
     *
     * @param temps nombre de temps a attendre 1s = 1000 ms
     */
    public void waitSomeTime(int temps) throws InterruptedException {
        long tempsDePause = System.currentTimeMillis() + temps;
        while (System.currentTimeMillis() < tempsDePause) {
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

    public void run() {
        System.out.println("Demarrage du Thread Process");
        while (!terminated) {
            try {
                strucEntrees = (StrucEntrees) lancement.getStrucEntrees().clone();
                strucSorties = lancement.getStrucSorties();

                if (lancement.getStrucProcess().getEtape() > 1 && lancement.getStrucEntrees().isdE1() && lancement.getStrucProcess().getEtape() != 100) {
                    semaphore_MSG = false;
                    semaphore_Valeur_Analogique = false;
                    resetCptAndTempo();
                    svg_etape = lancement.getStrucProcess().getEtape();
                    setAllSortiesToFalse();
                    lancement.getStrucProcess().setEtape(100);
                    tempsCourrant = System.currentTimeMillis();
                }
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            switch (lancement.getStrucProcess().getEtape()) {
                case 0:
                    setAllSortiesToFalse();
                    if (!semaphore_MSG) {
                        System.out.println("Appuyez sur le bouton 1");
                        semaphore_MSG = true;
                    }

                    if (strucEntrees.isdE1()) {
                        resetCptAndTempo();
                        semaphore_MSG = false;
                        lancement.getStrucProcess().setEtape(1);
                        strucSorties.setdS1(true);
                        System.out.println("ETAPE : 1 Machine en marche !");

                    }
                    break;
                case 1:
                    if (isTimeElapsed(250)) {
                        resetCptAndTempo();
                        lancement.getStrucProcess().setEtape(2);
                        System.out.println("ETAPE 2 : Le Conteneur se deplace");
                    }
                    break;
                case 2:
                    if (isTimeElapsed(30000)) {

                        if (isTimeElapsed(cpt * 500)) {
                            strucSorties.setdS2(!strucSorties.isdS2());
                            cpt++;
                        } else {
                            System.out.println("Attendez encore " + ((tempo + 30000) - System.currentTimeMillis()) / 1000 + " secondes pour le conteneur");
                        }
                    } else {
                        strucSorties.setdS2(true);
                        System.out.println("Le conteneur est en position passage à l'étape 3");
                        lancement.getStrucProcess().setEtape(3);
                    }
                    break;
                case 3:
                    int niveauRemplissageMin = lancement.getStrucProcess().getNiveauRemplissageMin();
                    int niveauRemplissageMax = lancement.getStrucProcess().getNiveauRemplissageMax();
                    int anaE1 = strucEntrees.getAnaE1();
                    System.out.println("La valeur de Potentiomètre 1 " + anaE1 + " doit être entre ] " + niveauRemplissageMin + " ; " + niveauRemplissageMax + " [");
                    if (anaE1 > niveauRemplissageMin && anaE1 < niveauRemplissageMax) {
                        strucSorties.setdS3(true);
                        resetCptAndTempo();
                        lancement.getStrucProcess().setEtape(4);
                        System.out.println("Le conteneur est correctement rempli. Passage à l'étape 4.");
                    }
                    break;
                case 4:
                    if (!semaphore_MSG) {
                        System.out.println("Appuyez sur le bouton 2");
                        semaphore_MSG = true;
                    }

                    if (strucEntrees.isdE2()) {
                        strucSorties.setdS2(false);
                        System.out.println("Le conteneur se déplace vers le poste de contrôle. Passage à l'étape 5");
                        resetCptAndTempo();
                        semaphore_MSG = false;
                        lancement.getStrucProcess().setEtape(5);
                    }
                    break;
                case 5:
                    if (isTimeElapsed(30000)) {
                        if (isTimeElapsed(cpt * 500)) {
                            lancement.getStrucSorties().setdS4(!lancement.getStrucSorties().isdS4());
                            cpt++;
                        } else {
                            System.out.println("Attendez encore " + ((tempo + 30000) - System.currentTimeMillis()) / 1000 + " secondes pour le conteneur");

                        }
                    } else {

                        System.out.println("Le conteneur est en position");
                        lancement.getStrucProcess().setEtape(6);
                    }
                    break;
                case 6:
                    resetCptAndTempo();
                    lancement.getStrucProcess().setEtape(7);
                    break;
                case 7:
                    if (!semaphore_Valeur_Analogique) {
                        System.out.println("Valeur du potentiomètre 2 " + strucEntrees.getAnaE2());
                    }

                    if (strucEntrees.getAnaE2() > lancement.getStrucProcess().getPoidsMinimum() && strucEntrees.getAnaE2() < lancement.getStrucProcess().getPoidsMaximum()) {

                        if (isTimeElapsed(45000)) {
                            if (isTimeElapsed(cpt * 1000)) {
                                strucSorties.setdS5(true);
                                System.out.println("Attendez encore " + ((tempo + 45000) - System.currentTimeMillis()) / 1000 + " secondes pour le poids du conteneur");
                                cpt++;
                            }
                        } else {
                            semaphore_Valeur_Analogique = true;
                            System.out.println("le poid du containers est correct pasage a l'étape 8");
                            semaphore_MSG = false;
                            lancement.getStrucProcess().setEtape(8);
                        }

                    } else {
                        System.out.println("le poid du containers n'est pas correct");
                        if (isTimeElapsed(45000)) {//boucle de 45 s
                            System.out.println("Attendez encore " + ((tempo + 45000) - System.currentTimeMillis()) / 1000 + " secondes pour le conteneur");
                            if (isTimeElapsed(cpt * 500)) {
                                lancement.getStrucSorties().setdS5(!lancement.getStrucSorties().isdS5());
                                cpt++;
                            }
                        }
                    }
                    break;
                case 8:
                    if (!semaphore_MSG) {
                        System.out.println("Appuyez sur le bouton 3");
                        semaphore_MSG = true;
                    }
                    if (strucEntrees.isdE3()) {
                        System.out.println("Le containers se déplace vers le poste d'expédition. passage a l'étape 9");
                        strucSorties.setdS4(false);
                        resetCptAndTempo();
                        semaphore_MSG = false;
                        semaphore_Valeur_Analogique = false;
                        lancement.getStrucProcess().setEtape(9);
                    }
                    break;
                case 9:
                    if (isTimeElapsed(30000)) {
                        if (isTimeElapsed(cpt * 500)) {
                            lancement.getStrucSorties().setdS6(!lancement.getStrucSorties().isdS6());
                            cpt++;
                        } else {
                            System.out.println("Attendez encore " + ((tempo + 30000) - System.currentTimeMillis()) / 1000 + " secondes pour le conteneur");
                        }
                    } else {
                        strucSorties.setdS6(true);
                        System.out.println("Le conteneur est en position passage a l'étape 10");
                        lancement.getStrucProcess().setEtape(10);
                    }
                    break;
                case 10:
                    System.out.println("Valeur du potentiomètre 2 " + strucEntrees.getAnaE2());
                    if (strucEntrees.getAnaE2() > lancement.getStrucProcess().getPoidsMinimum() && strucEntrees.getAnaE2() < lancement.getStrucProcess().getPoidsMaximum()) {
                        System.out.println("Poids correct");
                        System.out.println("Appuyez sur le bouton 4");
                        if (strucEntrees.isdE4()) {

                            strucSorties.setdS8(true);
                            resetCptAndTempo();
                            semaphore_MSG = false;
                            System.out.println("Passage a l'étape 11");
                            lancement.getStrucProcess().setEtape(11);
                        }
                    } else {
                        System.out.println("mise au rebut du container");
                        lancement.getStrucSorties().setdS7(true);
                    }
                    break;
                case 11:
                    if (!semaphore_MSG) {
                        System.out.println("Valeur du potentiomètre 2 " + strucEntrees.getAnaE2());

                    }
                    if (strucEntrees.getAnaE2() > lancement.getStrucProcess().getPoidsMinimum() && strucEntrees.getAnaE2() < lancement.getStrucProcess().getPoidsMaximum()) {
                        semaphore_MSG = true;

                        if (isTimeElapsed(15000)) {
                            System.out.println("Attendez encore " + ((tempo + 15000) - System.currentTimeMillis()) / 1000 + " secondes");
                        } else {
                            strucSorties.setdS3(false);
                            strucSorties.setdS5(false);
                            strucSorties.setdS6(false);
                            strucSorties.setdS8(false);
                            resetCptAndTempo();
                            System.out.println("--FIN DU PROCESS --");
                            System.out.println("REPRISE à l'étape 1");
                            semaphore_MSG = false;
                            semaphore_Valeur_Analogique = false;
                            lancement.getStrucProcess().setEtape(1);
                        }
                    } else {
                        if (!semaphore_MSG) {
                            semaphore_MSG = true;
                            System.out.println("Appuyez sur le bouton 4 car poids incorrecte");
                        }

                        if (strucEntrees.isdE4()) {
                            strucSorties.setdS3(false);
                            strucSorties.setdS5(false);
                            strucSorties.setdS7(false);
                            resetCptAndTempo();
                            System.out.println("--FIN DU PROCESS --");
                            System.out.println("REPRISE à l'étape 1");
                            semaphore_MSG = false;
                            semaphore_Valeur_Analogique = false;
                            lancement.getStrucProcess().setEtape(1);
                        }
                    }
                    break;
                case 100:
                    if (!semaphore_MSG) {
                        System.out.println("DEFAUT SUR LA LIGNE APPUYEZ SUR E5 pour RAZ");
                        semaphore_MSG = true;
                    }

                    if (isTimeElapsed(1000)) {
                        if (isTimeElapsed(cpt * 1000)) {
                            strucSorties.setdS1(!strucSorties.isdS1());
                            strucSorties.setdS2(!strucSorties.isdS2());
                            strucSorties.setdS3(!strucSorties.isdS3());
                            strucSorties.setdS4(!strucSorties.isdS4());
                            strucSorties.setdS5(!strucSorties.isdS5());
                            strucSorties.setdS6(!strucSorties.isdS6());
                            strucSorties.setdS7(!strucSorties.isdS7());
                            strucSorties.setdS8(!strucSorties.isdS8());
                            cpt++;
                        }

                        if (lancement.getStrucEntrees().isdE5()) {
                            resetCptAndTempo();
                            setAllSortiesToFalse();
                            System.out.println("REPRISE à L'ETAPE : " + svg_etape);
                            semaphore_MSG = false;
                            lancement.getStrucProcess().setEtape(svg_etape);
                            break;
                        }
                    } else {
                        resetCptAndTempo();
                    }
                default:
                    break;
            }

            try {
                waitSomeTime(CYCLE_PROCESS); // équivalent du Thread.Sleep(CycleProcess) est bloquant...
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Fin du thread Process!");
    }

    private void setAllSortiesToFalse() {
        strucSorties.setdS1(false);
        strucSorties.setdS2(false);
        strucSorties.setdS3(false);
        strucSorties.setdS4(false);
        strucSorties.setdS5(false);
        strucSorties.setdS6(false);
        strucSorties.setdS7(false);
        strucSorties.setdS8(false);
    }

    private void resetCptAndTempo() {
        cpt = 0;
        tempo = System.currentTimeMillis();
    }

    private boolean isTimeElapsed(int delay) {
        return (System.currentTimeMillis() - tempo) >= delay;
    }


}
