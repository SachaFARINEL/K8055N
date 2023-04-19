package pkprocess;

import k8055n.K8055N;

import java.util.concurrent.TimeUnit;

/**
 * This class represents a thread that reads the inputs of a K8055N device at regular intervals.
 * The inputs are stored in a {@link StrucEntrees} object contained in the {@link Lancement} object passed to the constructor.
 */
public class Th_Entrees extends Thread {

    /**
     * Correspond au temps de cycle pour la lecture des canaux d'entrées en millisecondes.
     */
    private static final int CYCLE_ENTREES = 10;

    /**
     * Les constantes CHANNEL_1, CHANNEL_2, CHANNEL_3, CHANNEL_4, CHANNEL_5 correspondent aux numéros des canaux d'entrées digitaux et analogiques.
     */
    private static final int CHANNEL_1 = 1;
    private static final int CHANNEL_2 = 2;
    private static final int CHANNEL_3 = 3;
    private static final int CHANNEL_4 = 4;
    private static final int CHANNEL_5 = 5;

    /**
     * Booléen qui permet de terminer le thread.
     */
    private boolean terminated = false;

    /**
     * Lancement est une instance de la classe Lancement qui permet d'accéder à la carte K8055N et à la structure de données StrucEntrees.
     */
    private final Lancement lancement;

    /**
     * Constructs a new {@code Th_Entrees} thread with the specified {@link Lancement} object.
     *
     * @param lancement the {@link Lancement} object containing the {@link StrucEntrees} object to update.
     */
    public Th_Entrees(Lancement lancement) {
        this.lancement = lancement;
    }

    /**
     * Signals this thread to stop execution.
     */
    public void finish() {
        terminated = true;
    }

    /**
     * Main method of the thread. Reads the inputs of the K8055N device at regular intervals and updates
     * the {@link StrucEntrees} object contained in the {@link Lancement} object passed to the constructor.
     */
    public void run() {
        System.out.println("Demarrage du Thread Entrees");
        while (!terminated) {
            synchronized (lancement.getLockObjCarte()) {
                readChannels();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(CYCLE_ENTREES);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        System.out.println("Fin du thread Entrees!");
    }

    /**
     * Reads the inputs of the K8055N device and updates the {@link StrucEntrees} object contained in the
     * {@link Lancement} object passed to the constructor.
     */
    private void readChannels() {
        StrucEntrees strucEntrees = lancement.getStrucEntrees();
        K8055N carte = lancement.getCarte();
        strucEntrees.setdE1(carte.ReadDigitalChannel(CHANNEL_1));
        strucEntrees.setdE2(carte.ReadDigitalChannel(CHANNEL_2));
        strucEntrees.setdE3(carte.ReadDigitalChannel(CHANNEL_3));
        strucEntrees.setdE4(carte.ReadDigitalChannel(CHANNEL_4));
        strucEntrees.setdE5(carte.ReadDigitalChannel(CHANNEL_5));
        strucEntrees.setAnaE1(carte.ReadAnalogChannel(CHANNEL_1));
        strucEntrees.setAnaE2(carte.ReadAnalogChannel(CHANNEL_2));
    }
}
