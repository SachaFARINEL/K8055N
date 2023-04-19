package pkprocess;

/**
 * La classe Lanceur est le point d'entrée de l'application.
 * Elle crée une instance de la classe Lancement et lance la machine.
 *
 * @author [FARINEL Sacha, HERVOUËT Clément, ATTARD Aurélia]
 * @version 1.0
 */
public class Lanceur {
    /**
     * Méthode principale de la classe Lanceur qui crée une instance de Lancement et lance la machine.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Lancement lancement = new Lancement();
        lancement.lance();
    }
}
