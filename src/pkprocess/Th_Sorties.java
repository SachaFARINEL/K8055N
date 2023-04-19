package pkprocess;

import java.util.concurrent.TimeUnit;

/**
 * Th_Sorties est une classe qui représente un thread qui gère les sorties du système.
 * Il s'occupe de récupérer la structure de sorties de lancement, de la cloner et d'envoyer les valeurs analogiques
 * ainsi que les signaux digitaux associés aux différentes sorties du système en utilisant l'objet de carte associé.
 */
public class Th_Sorties extends Thread {

    /**
     * Variable pour savoir si le thread doit se terminer
     */
    private boolean terminated = false;

    /**
     * Cycle d'envoi des sorties en millisecondes
     */
    private static final int CYCLE_SORTIE = 10;

    /**
     * Objet Lancement associé au thread
     */
    private final Lancement lancement;

    /**
     * Constructeur de Th_Sorties
     *
     * @param lancement objet Lancement associé au thread
     */
    public Th_Sorties(Lancement lancement) {
        this.lancement = lancement;
    }

    /**
     * Permet de terminer la thread
     */
    public void finish() {
        terminated = true;
    }

    /**
     * Gère l'envoi des valeurs analogiques et des signaux digitaux associés aux sorties du système en utilisant l'objet
     * de carte associé. Utilise une boucle while qui s'arrête lorsque la variable terminated est true. Récupère la
     * structure de sorties de lancement, la clone et envoie les valeurs analogiques ainsi que les signaux digitaux.
     */
    public void run() {
        System.out.println("Demarrage du Thread Sorties");
        while (!terminated) {
            synchronized (lancement.getLockObjCarte()) {
                StrucSorties tmp;
                try {
                    tmp = (StrucSorties) lancement.getStrucSorties().clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }

                lancement.getCarte().OutputAnalogChannel(1, tmp.getAnaS1());
                lancement.getCarte().OutputAnalogChannel(2, tmp.getAnaS2());

                if (tmp.isdS1()) {
                    lancement.getCarte().SetDigitalChannel(1);
                } else {
                    lancement.getCarte().ClearDigitalChannel(1);
                }

                if (tmp.isdS2()) {
                    lancement.getCarte().SetDigitalChannel(2);
                } else {
                    lancement.getCarte().ClearDigitalChannel(2);
                }

                if (tmp.isdS3()) {
                    lancement.getCarte().SetDigitalChannel(3);
                } else {
                    lancement.getCarte().ClearDigitalChannel(3);
                }

                if (tmp.isdS4()) {
                    lancement.getCarte().SetDigitalChannel(4);

                } else {
                    lancement.getCarte().ClearDigitalChannel(4);
                }

                if (tmp.isdS5()) {
                    lancement.getCarte().SetDigitalChannel(5);

                } else {
                    lancement.getCarte().ClearDigitalChannel(5);
                }

                if (tmp.isdS6()) {
                    lancement.getCarte().SetDigitalChannel(6);

                } else {
                    lancement.getCarte().ClearDigitalChannel(6);
                }

                if (tmp.isdS7()) {
                    lancement.getCarte().SetDigitalChannel(7);

                } else {
                    lancement.getCarte().ClearDigitalChannel(7);
                }

                if (tmp.isdS8()) {
                    lancement.getCarte().SetDigitalChannel(8);
                } else {
                    lancement.getCarte().ClearDigitalChannel(8);
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(CYCLE_SORTIE);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
        System.out.println("Fin du thread Sorties!");
    }
}
