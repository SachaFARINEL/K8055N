package pkprocess;

import java.util.concurrent.TimeUnit;

/**
 * Thread qui gère l'interface homme-machine (IHM) pour une application.
 * Ce thread s'occupe de copier les données des structures de données associées à la classe Lancement et d'exécuter le traitement
 * associé à l'IHM à intervalles réguliers définis par la constante CYCLE_IHM.
 * Les données copiées sont stockées dans les objets volatiles strucEntrees, strucSorties et strucProcess.
 */
public class Th_IHM extends Thread {

    private volatile boolean terminated = false;

    private final Lancement lancement;

    private volatile StrucEntrees strucEntrees;

    private volatile StrucSorties strucSorties;

    private volatile StrucProcess strucProcess;

    /**
     * Constructeur de la classe Th_IHM.
     *
     * @param lancement objet Lancement contenant les objets StrucEntrees, StrucSorties et StrucProcess à traiter.
     */
    public Th_IHM(Lancement lancement) {
        this.lancement = lancement;
    }

    /**
     * Méthode pour terminer le thread Th_IHM.
     * Cette méthode met la variable d'état "terminated" à true, ce qui permet de sortir de la boucle while dans la méthode run().
     */
    public void finish() {
        terminated = true;
    }

    /**
     * Méthode d'exécution du thread Th_IHM.
     * Cette méthode effectue un traitement périodique à intervalle régulier pour récupérer les objets de type StrucEntrees, StrucSorties et StrucProcess de l'objet Lancement
     * et effectue un traitement sur ces données en appelant la méthode privée traitementIHM().
     * Si le thread est interrompu, il affiche la trace de la pile d'exception.
     */
    public void run() {
        System.out.println("Démarrage du Thread IHM");
        while (!terminated) {
            synchronized (lancement) {
                strucEntrees = lancement.getStrucEntrees();
                strucSorties = lancement.getStrucSorties();
                strucProcess = lancement.getStrucProcess();
            }

            traitementIHM();

            try {
                int CYCLE_IHM = 10;
                TimeUnit.MILLISECONDS.sleep(CYCLE_IHM);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        System.out.println("Fin du thread IHM!");
    }

    /**
     * Méthode privée pour traiter les données des objets StrucEntrees, StrucSorties et StrucProcess.
     * Cette méthode est appelée à chaque cycle de traitement de la méthode run().
     * Elle effectue un traitement sur ces données.
     */
    private void traitementIHM() {
        //TODO
    }
}
