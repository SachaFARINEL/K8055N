package pkprocess;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the input structure containing 2 analog inputs and 5 digital inputs.
 * It is marked as an XmlRootElement, meaning it can be used for XML serialization and deserialization.
 * It also implements the Cloneable interface, allowing for deep copying of objects.
 */
@XmlRootElement
public final class StrucEntrees implements Cloneable {
    /**
     * The value of analog input 1.
     */
    private int anaE1 = 0;

    /**
     * The value of analog input 2.
     */
    private int anaE2 = 0;

    /**
     * The state of digital input 1.
     */
    private boolean dE1 = false;

    /**
     * The state of digital input 2.
     */
    private boolean dE2 = false;

    /**
     * The state of digital input 3.
     */
    private boolean dE3 = false;

    /**
     * The state of digital input 4.
     */
    private boolean dE4 = false;

    /**
     * The state of digital input 5.
     */
    private boolean dE5 = false;

    /**
     * Returns a new StrucEntrees object that is a deep copy of this instance.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if cloning is not supported for this object.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns the value of analog input 1.
     *
     * @return the value of analog input 1.
     */
    public int getAnaE1() {
        return anaE1;
    }

    /**
     * Sets the value of analog input 1.
     *
     * @param anaE1 the value of analog input 1.
     */
    public void setAnaE1(int anaE1) {
        this.anaE1 = anaE1;
    }

    /**
     * Returns the value of analog input 2.
     *
     * @return the value of analog input 2.
     */
    public int getAnaE2() {
        return anaE2;
    }

    /**
     * Sets the value of analog input 2.
     *
     * @param anaE2 the value of analog input 2.
     */
    public void setAnaE2(int anaE2) {
        this.anaE2 = anaE2;
    }

    /**
     * Returns the state of digital input 1.
     *
     * @return the state of digital input 1.
     */
    public boolean isdE1() {
        return dE1;
    }

    /**
     * Sets the state of digital input 1.
     *
     * @param dE1 the state of digital input 1.
     */
    public void setdE1(boolean dE1) {
        this.dE1 = dE1;
    }

    /**
     * Returns the state of digital input 2.
     *
     * @return the state of digital input 2.
     */
    public boolean isdE2() {
        return dE2;
    }

    /**
     * Sets the state of digital input 2.
     *
     * @param dE2 the state of digital input 2.
     */
    public void setdE2(boolean dE2) {
        this.dE2 = dE2;
    }

    /**
     * Returns the state of digital input 3.
     *
     * @return the state of digital input 3.
     */
    public boolean isdE3() {
        return dE3;
    }

    /**
     * Sets the state of digital input 3.
     *
     * @param dE3 the state of digital input 3.
     */
    public void setdE3(boolean dE3) {
        this.dE3 = dE3;
    }

    /**
     * Returns the state of digital input 4.
     *
     * @return the state of digital input 4.
     */
    public boolean isdE4() {
        return dE4;
    }

    /**
     * Sets the state of digital input 4.
     *
     * @param dE4 the state of digital input 4.
     */
    public void setdE4(boolean dE4) {
        this.dE4 = dE4;
    }

    /**
     * Returns the state of digital input 5.
     *
     * @return the state of digital input 5.
     */
    public boolean isdE5() {
        return dE5;
    }

    /**
     * Sets the state of digital input 5.
     *
     * @param dE5 the state of digital input 5.
     */
    public void setdE5(boolean dE5) {
        this.dE5 = dE5;
    }
}
