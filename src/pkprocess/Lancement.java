package pkprocess;

import k8055n.K8055N;

import java.io.FileNotFoundException;

/**
 * Classe permettant le lancement du processus de fabrication.
 */
public class Lancement {
    /**
     * Numéro du dispositif utilisé pour la carte K8055N.
     */
    private static final int DEVICE_NUMBER = 0;

    /**
     * Instance unique de la carte K8055N pour l'ensemble de l'application.
     */
    private final K8055N carte;

    /**
     * Objet de synchronisation pour la carte.
     */
    private final Object lockObjCarte = new Object();

    /**
     * Structure d'entrées pour la carte.
     */
    private final StrucEntrees strucEntrees = new StrucEntrees();

    /**
     * Structure de sorties pour la carte.
     */
    private final StrucSorties strucSorties = new StrucSorties();

    /**
     * Structure de processus pour la machine.
     */
    private final StrucProcess strucProcess = new StrucProcess();

    /**
     * Thread pour les entrées de la machine.
     */
    private Th_Entrees thEntrees;

    /**
     * Thread pour les sorties de la machine.
     */
    private Th_Sorties thSorties;

    /**
     * Thread pour le processus de fabrication de la machine.
     */
    private Th_Process thProcess;

    /**
     * Thread pour l'interface utilisateur de la machine.
     */
    private Th_IHM thIhm;

    /**
     * Constructeur de la classe Lancement. Initialise l'instance unique de la carte K8055N.
     */
    public Lancement() {
        System.out.println("Création de la carte K8055N !");
        carte = K8055NSingleton.getInstance();
    }

    /**
     * Lance le processus de fabrication de la machine.
     *
     * @return true si le processus est en cours, false sinon.
     */
    public boolean lance() {
        try {
            if (isCardConnected()) {
                if (strucProcess.getEtape() == 0) {
                    startThreads();
                } else {
                    System.out.println("Une fabrication est en cours !");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * Arrête le processus de fabrication de la machine.
     */
    public void arret() {
        System.out.println("Demande d'arrêt de la machine !");
        try {
            endThreads();
        } catch (InterruptedException e) {
            System.err.println("Une erreur est survenue pendant l'arrêt de la machine : " + e.getMessage());
        }

        try {
            carte.CloseDevice();
            System.out.println("Fermeture de la liaison avec la carte K8055.");
        } catch (Exception e) {
            System.err.println("Une erreur est survenue pendant la fermeture de la liaison avec la carte K8055 : " + e.getMessage());
        }
    }

    /**
     * Remet à zéro le processus de fabrication de la machine.
     */
    public void raz() {
        this.strucProcess.setEtape(0);
        System.out.println("Raz du Process !");
    }

    /**
     * Démarre les threads pour les entrées, les sorties et le processus de fabrication de la machine.
     */
    private void startThreads() {
        thEntrees = new Th_Entrees(this);
        thSorties = new Th_Sorties(this);
        thProcess = new Th_Process(this);
        thSorties.start();
        thProcess.start();
        thEntrees.start();
        System.out.println("Lancement des Thread Ok !");
    }

    /**
     * Arrête la machine en appelant la méthode join() sur les threads d'entrées, de sorties, de processus et d'interface graphique.
     * Les méthodes Finish() de ces threads sont également appelées pour arrêter les boucles de traitement.
     *
     * @throws InterruptedException si une interruption est survenue pendant l'attente des threads.
     */
    private void endThreads() throws InterruptedException {
        thProcess.join();
        thSorties.join();
        thEntrees.join();
        thIhm.join();
        thEntrees.finish();
        thSorties.finish();
        thProcess.finish();
        thIhm.finish();
        System.out.println("La machine est à l'arrêt !");
    }

    /**
     * Vérifie si la carte K8055N est connectée et opérationnelle en appelant la méthode OpenDevice(DEVICE_NUMBER)
     *
     * @return true si la carte est connectée et opérationnelle, false sinon
     * @throws FileNotFoundException si le fichier K8055D.DLL n'a pas été trouvé
     */
    private boolean isCardConnected() throws FileNotFoundException {
        int cardValue = carte.OpenDevice(DEVICE_NUMBER);

        if (cardValue == -10) {
            throw new FileNotFoundException("K8055D.DLL file not found!");
        } else if (cardValue >= 0) {
            System.out.println("Liaison carte OK !");
            return true;
        } else {
            System.out.println("Carte absente !");
            return false;
        }
    }

    public K8055N getCarte() {
        return this.carte;
    }

    public Object getLockObjCarte() {
        return this.lockObjCarte;
    }

    public StrucEntrees getStrucEntrees() {
        return this.strucEntrees;
    }

    public StrucSorties getStrucSorties() {
        return this.strucSorties;
    }

    public StrucProcess getStrucProcess() {
        return this.strucProcess;
    }
}
