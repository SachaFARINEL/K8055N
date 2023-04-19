package pksw;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/k8055etat")
public class SW_Access_Etat {


    public SW_Access_Etat() {

    }

    // lecture des entr�es (Version navigateur)
    @GET
    @Path("/entrees")
    @Produces(MediaType.TEXT_PLAIN)
    public String LireEntrees() {
        String Str;
        Str = "E1 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE1() + " - " + "E2 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE2() + " - " +
                "E3 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE3() + " - " + "E4 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE4() + " - " +
                "E5 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE5();
        return Str;
    }

    // lecture de l'objet StrucEntrees
    @GET
    @Path("/strucentrees")
    @Produces(MediaType.TEXT_PLAIN)
    public String LireStrucEntrees() {
        String Str = "SE";
        //StrucEntrees tmp = (StrucEntrees)SW_Access_Action.Lanceur.STE.clone();
        Str = "E1 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE1() + " - " + "E2 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE2() + " - " +
                "E3 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE3() + " - " + "E4 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE4() + " - " +
                "E5 : " + SW_Access_Action.Lanceur.getStrucEntrees().isdE5() + " - " + "A1 : " + SW_Access_Action.Lanceur.getStrucEntrees().getAnaE1() + " - " + "A2 : " + SW_Access_Action.Lanceur.getStrucEntrees().getAnaE2();
        return Str;
    }

    // lecture des sorties (Version navigateur)
    @GET
    @Path("/sorties")
    @Produces(MediaType.TEXT_PLAIN)
    public String sorties() {
        String Str = "S";
        Str = "E1 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS1() + " - " + "E2 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS2() + " - " +
                "E3 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS3() + " - " + "E4 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS4() + " - " +
                "E5 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS5();
        return Str;
    }

    // lecture de l'objet StrucSorties
    @GET
    @Path("/strucsorties")
    @Produces(MediaType.TEXT_PLAIN)
    public String StrucSorties() {
        String Str = "SS";
        //StrucSorties tmp = (StrucSorties)SW_Access_Action.Lanceur.STS.clone();
        Str = "E1 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS1() + " - " + "E2 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS2() + " - " +
                "E3 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS3() + " - " + "E4 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS4() + " - " +
                "E5 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS5() + " - " + "E6 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS6() + " - " +
                " - " + "E7 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS7() + " - " + "E8 : " + SW_Access_Action.Lanceur.getStrucSorties().isdS8() +
                "A1 : " + SW_Access_Action.Lanceur.getStrucSorties().getAnaS1() + " - " + "A2 : " + SW_Access_Action.Lanceur.getStrucSorties().getAnaS2();
        return Str;
    }

    // lecture du Process (Version navigateur)
    @GET
    @Path("/process")
    @Produces(MediaType.TEXT_PLAIN)
    public String process() {
        String Str = "P";
        Str = "Etape du processus : " + SW_Access_Action.Lanceur.getStrucProcess().getEtape();
        return Str;
    }

    // lecture de l'objet StrucProcess
    @GET
    @Path("/strucprocess")
    @Produces(MediaType.TEXT_PLAIN)
    public String StrucProcess() {
        String Str = "SP";
        //StrucProcess tmp = (StrucProcess)SW_Access_Action.Lanceur.STP.clone();
        Str = "Etape : " + SW_Access_Action.Lanceur.getStrucProcess().getEtape() + " - " + "Niveau_Remplissage_Min : " + SW_Access_Action.Lanceur.getStrucProcess().getNiveauRemplissageMin() + " - " +
                "Niveau_Remplissage_Max : " + SW_Access_Action.Lanceur.getStrucProcess().getNiveauRemplissageMax() + " - " + "Poids_Minimum : " + SW_Access_Action.Lanceur.getStrucProcess().getPoidsMinimum() + " - " +
                "Poids_Maximum : " + SW_Access_Action.Lanceur.getStrucProcess().getPoidsMaximum();
        return Str;
    }

}

