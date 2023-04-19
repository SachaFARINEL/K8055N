package pkprocess;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cette classe représente une structure de données contenant les sorties d'un processus.
 * Elle permet de stocker les valeurs de différents capteurs et actionneurs.
 * Elle est clonable.
 */
@XmlRootElement
public final class StrucSorties implements Cloneable {

    /**
     * L'état de la première sortie numérique.
     */
    private boolean dS1 = false;

    /**
     * L'état de la deuxième sortie numérique.
     */
    private boolean dS2 = false;

    /**
     * L'état de la troisième sortie numérique.
     */
    private boolean dS3 = false;

    /**
     * L'état de la quatrième sortie numérique.
     */
    private boolean dS4 = false;

    /**
     * L'état de la cinquième sortie numérique.
     */
    private boolean dS5 = false;

    /**
     * L'état de la sixième sortie numérique.
     */
    private boolean dS6 = false;

    /**
     * L'état de la septième sortie numérique.
     */
    private boolean dS7 = false;

    /**
     * L'état de la huitième sortie numérique.
     */
    private boolean dS8 = false;

    /**
     * Permet de cloner la structure de données.
     *
     * @return une copie de la structure de données.
     * @throws CloneNotSupportedException si le clonage est impossible.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Obtient la valeur du premier capteur analogique de sortie.
     *
     * @return la valeur du premier capteur analogique de sortie.
     */
    public int getAnaS1() {
        return 0;
    }

    /**
     * Obtient la valeur du deuxième capteur analogique de sortie.
     *
     * @return la valeur du deuxième capteur analogique de sortie.
     */
    public int getAnaS2() {
        return 0;
    }

    /**
     * Obtient l'état de la première sortie numérique.
     *
     * @return l'état de la première sortie numérique.
     */
    public boolean isdS1() {
        return dS1;
    }

    /**
     * Modifie l'état de la première sortie numérique.
     *
     * @param dS1 le nouvel état de la première sortie numérique.
     */
    public void setdS1(boolean dS1) {
        this.dS1 = dS1;
    }

    /**
     * Obtient l'état de la deuxième sortie numérique.
     *
     * @return l'état de la deuxième sortie numérique.
     */
    public boolean isdS2() {
        return dS2;
    }

    /**
     * Modifie l'état de la deuxième sortie numérique.
     *
     * @param dS2 le nouvel état de la deuxième sortie numérique.
     */
    public void setdS2(boolean dS2) {
        this.dS2 = dS2;
    }

    /**
     * Obtient l'état de la 3 sortie numérique.
     *
     * @return l'état de la 3 sortie numérique.
     */
    public boolean isdS3() {
        return dS3;
    }

    /**
     * Modifie l'état de la 3 sortie numérique.
     *
     * @param dS3 le nouvel état de la 3 sortie numérique.
     */
    public void setdS3(boolean dS3) {
        this.dS3 = dS3;
    }

    /**
     * Obtient l'état de la 4 sortie numérique.
     *
     * @return l'état de la 4 sortie numérique.
     */
    public boolean isdS4() {
        return dS4;
    }

    /**
     * Modifie l'état de la 4 sortie numérique.
     *
     * @param dS4 le nouvel état de la 4 sortie numérique.
     */
    public void setdS4(boolean dS4) {
        this.dS4 = dS4;
    }

    /**
     * Obtient l'état de la 5 sortie numérique.
     *
     * @return l'état de la 5 sortie numérique.
     */
    public boolean isdS5() {
        return dS5;
    }

    /**
     * Modifie l'état de la 5 sortie numérique.
     *
     * @param dS5 le nouvel état de la 5 sortie numérique.
     */
    public void setdS5(boolean dS5) {
        this.dS5 = dS5;
    }

    /**
     * Obtient l'état de la 6 sortie numérique.
     *
     * @return l'état de la 6 sortie numérique.
     */
    public boolean isdS6() {
        return dS6;
    }

    /**
     * Modifie l'état de la 6 sortie numérique.
     *
     * @param dS6 le nouvel état de la 6 sortie numérique.
     */
    public void setdS6(boolean dS6) {
        this.dS6 = dS6;
    }

    /**
     * Obtient l'état de la 7 sortie numérique.
     *
     * @return l'état de la 7 sortie numérique.
     */
    public boolean isdS7() {
        return dS7;
    }

    /**
     * Modifie l'état de la 7 sortie numérique.
     *
     * @param dS7 le nouvel état de la 7 sortie numérique.
     */
    public void setdS7(boolean dS7) {
        this.dS7 = dS7;
    }

    /**
     * Obtient l'état de la 8 sortie numérique.
     *
     * @return l'état de la 8 sortie numérique.
     */
    public boolean isdS8() {
        return dS8;
    }

    /**
     * Modifie l'état de la 8 sortie numérique.
     *
     * @param dS8 le nouvel état de la 8 sortie numérique.
     */
    public void setdS8(boolean dS8) {
        this.dS8 = dS8;
    }
}
