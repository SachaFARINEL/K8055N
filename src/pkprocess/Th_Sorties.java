package pkprocess;

public class Th_Sorties extends Thread {

    private

    // Gestion de la fin du thread
    boolean Term = true;
    // Temps de Cycle d'�criture des sorties (en ms)
    int CycleSorties = 10;
    // R�f�rence sur l'objet Lancement
    Lancement m_L;

    public Th_Sorties(Lancement L) {
        m_L = L;
    }

    public void finish() {
        Term = false;
    }

    public void setCycleSorties(int Val) {
        CycleSorties = Val;
    }

    public void run() {

        System.out.println("Demarrage du Thread Sorties");

        while (Term) {

            // Mise � jour synchronis� des sorties physiques de la carte USB
            // en fonction de l'�tat des donn�es membres de l'objet m_L.STS
            //.
            //.
            //.


            synchronized (m_L.getLockObjCarte()) {

                //todo completer

                StrucSorties tmp = null;
                try {
                    tmp = (StrucSorties) m_L.getStrucSorties().clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }


                //pour les sorties analogiques avec les valeurs allant de 0 à 255 d'intensité
                //traduction aucune led allumé - full allumé !
                m_L.getCarte().OutputAnalogChannel(1, tmp.getAnaS1());
                m_L.getCarte().OutputAnalogChannel(2, tmp.getAnaS2());

                if (tmp.isdS1()) {
                    m_L.getCarte().SetDigitalChannel(1);

                } else {
                    m_L.getCarte().ClearDigitalChannel(1);
                }

                if (tmp.isdS2()) {
                    m_L.getCarte().SetDigitalChannel(2);

                } else {
                    m_L.getCarte().ClearDigitalChannel(2);
                }


                if (tmp.isdS3()) {
                    m_L.getCarte().SetDigitalChannel(3);

                } else {
                    m_L.getCarte().ClearDigitalChannel(3);
                }

                if (tmp.isdS4()) {
                    m_L.getCarte().SetDigitalChannel(4);

                } else {
                    m_L.getCarte().ClearDigitalChannel(4);
                }


                if (tmp.isdS5()) {
                    m_L.getCarte().SetDigitalChannel(5);

                } else {
                    m_L.getCarte().ClearDigitalChannel(5);
                }


                if (tmp.isdS6()) {
                    m_L.getCarte().SetDigitalChannel(6);

                } else {
                    m_L.getCarte().ClearDigitalChannel(6);
                }

                if (tmp.isdS7()) {
                    m_L.getCarte().SetDigitalChannel(7);

                } else {
                    m_L.getCarte().ClearDigitalChannel(7);
                }

                if (tmp.isdS8()) {
                    m_L.getCarte().SetDigitalChannel(8);

                } else {
                    m_L.getCarte().ClearDigitalChannel(8);
                }


            }

            try {
                Thread.sleep(CycleSorties);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
        System.out.println("Fin du thread Sorties!");
    }


}
