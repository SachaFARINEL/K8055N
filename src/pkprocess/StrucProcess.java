package pkprocess;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cette classe représente une structure de processus, avec des constantes pour le niveau de remplissage et le poids, ainsi qu'une étape courante.
 * Elle implémente l'interface Cloneable pour permettre la création d'une copie profonde de l'objet.
 */
@XmlRootElement
public final class StrucProcess implements Cloneable {

    /**
     * Niveau minimum de remplissage.
     */
    private static final int NIVEAU_REMPLISSAGE_MIN = 120;

    /**
     * Niveau maximum de remplissage.
     */
    private static final int NIVEAU_REMPLISSAGE_MAX = 130;

    /**
     * Poids minimum.
     */
    private static final int POIDS_MINIMUM = 123;

    /**
     * Poids maximum.
     */
    private static final int POIDS_MAXIMUM = 128;

    /**
     * Étape courante du processus.
     */
    private int etape = 0;

    /**
     * Retourne une copie de l'objet courant.
     *
     * @return Une copie de l'objet courant.
     * @throws CloneNotSupportedException Si la création d'une copie n'est pas supportée.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Retourne l'étape courante du processus.
     *
     * @return L'étape courante du processus.
     */
    public int getEtape() {
        return etape;
    }

    /**
     * Définit l'étape courante du processus.
     *
     * @param number La nouvelle étape courante du processus.
     */
    public void setEtape(int number) {
        this.etape = number;
    }

    /**
     * Retourne le niveau minimum de remplissage.
     *
     * @return Le niveau minimum de remplissage.
     */
    public int getNiveauRemplissageMin() {
        return NIVEAU_REMPLISSAGE_MIN;
    }

    /**
     * Retourne le niveau maximum de remplissage.
     *
     * @return Le niveau maximum de remplissage.
     */
    public int getNiveauRemplissageMax() {
        return NIVEAU_REMPLISSAGE_MAX;
    }

    /**
     * Retourne le poids minimum.
     *
     * @return Le poids minimum.
     */
    public int getPoidsMinimum() {
        return POIDS_MINIMUM;
    }

    /**
     * Retourne le poids maximum.
     *
     * @return Le poids maximum.
     */
    public int getPoidsMaximum() {
        return POIDS_MAXIMUM;
    }
}

