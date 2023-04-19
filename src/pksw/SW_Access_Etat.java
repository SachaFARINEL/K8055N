package pksw;


import pkprocess.Lancement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The SW_Access_Etat class provides access to the states of the inputs, outputs and the process of the application.
 * This class contains methods that return the states of the inputs and outputs, as well as the current step of the process.
 * The class also provides access to the structure of the inputs, outputs and the process, which contains additional details
 * about the states of the inputs and outputs, as well as the settings for the process.
 */
@Path("/k8055etat")
public class SW_Access_Etat {
    private static final Lancement LANCEMENT = SW_Access_Action.lancement;

    /**
     * Returns the states of the inputs as a plain text response.
     *
     * @return the states of the inputs
     */
    @GET
    @Path("/entrees")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lireEntrees() {
        StringBuilder sb = new StringBuilder();
        appendStates(sb, "E1", LANCEMENT.getStrucEntrees().isdE1());
        appendStates(sb, "E2", LANCEMENT.getStrucEntrees().isdE2());
        appendStates(sb, "E3", LANCEMENT.getStrucEntrees().isdE3());
        appendStates(sb, "E4", LANCEMENT.getStrucEntrees().isdE4());
        appendStates(sb, "E5", LANCEMENT.getStrucEntrees().isdE5());
        return Response.ok(sb.toString()).build();
    }

    /**
     * Returns the structure of the inputs as a plain text response.
     *
     * @return the structure of the inputs
     */
    @GET
    @Path("/strucentrees")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lireStrucEntrees() {
        StringBuilder sb = new StringBuilder();
        appendStates(sb, "E1", LANCEMENT.getStrucEntrees().isdE1());
        appendStates(sb, "E2", LANCEMENT.getStrucEntrees().isdE2());
        appendStates(sb, "E3", LANCEMENT.getStrucEntrees().isdE3());
        appendStates(sb, "E4", LANCEMENT.getStrucEntrees().isdE4());
        appendStates(sb, "E5", LANCEMENT.getStrucEntrees().isdE5());
        appendStates(sb, "A1", LANCEMENT.getStrucEntrees().getAnaE1());
        appendStates(sb, "A2", LANCEMENT.getStrucEntrees().getAnaE2());
        return Response.ok(sb.toString()).build();
    }

    /**
     * Returns the states of the outputs as a plain text response.
     *
     * @return the states of the outputs
     */
    @GET
    @Path("/sorties")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lireSorties() {
        StringBuilder sb = new StringBuilder();
        appendStates(sb, "S1", LANCEMENT.getStrucSorties().isdS1());
        appendStates(sb, "S2", LANCEMENT.getStrucSorties().isdS2());
        appendStates(sb, "S3", LANCEMENT.getStrucSorties().isdS3());
        appendStates(sb, "S4", LANCEMENT.getStrucSorties().isdS4());
        appendStates(sb, "S5", LANCEMENT.getStrucSorties().isdS5());
        return Response.ok(sb.toString()).build();
    }

    /**
     * Returns the states of the outputs as a plain text response.
     *
     * @return the states of the outputs
     */
    @GET
    @Path("/strucsorties")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lireStrucSorties() {
        StringBuilder sb = new StringBuilder();
        appendStates(sb, "E1", LANCEMENT.getStrucSorties().isdS1());
        appendStates(sb, "E2", LANCEMENT.getStrucSorties().isdS2());
        appendStates(sb, "E3", LANCEMENT.getStrucSorties().isdS3());
        appendStates(sb, "E4", LANCEMENT.getStrucSorties().isdS4());
        appendStates(sb, "E5", LANCEMENT.getStrucSorties().isdS5());
        appendStates(sb, "E6", LANCEMENT.getStrucSorties().isdS6());
        appendStates(sb, "E7", LANCEMENT.getStrucSorties().isdS7());
        appendStates(sb, "E8", LANCEMENT.getStrucSorties().isdS8());
        appendStates(sb, "A1", LANCEMENT.getStrucSorties().getAnaS1());
        appendStates(sb, "A2", LANCEMENT.getStrucSorties().getAnaS2());
        return Response.ok(sb.toString()).build();
    }

    /**
     * Returns the states of the outputs as a plain text response.
     *
     * @return the states of the outputs
     */
    @GET
    @Path("/process")
    @Produces(MediaType.TEXT_PLAIN)
    public Response process() {
        return Response.ok("Etape du processus : " + SW_Access_Action.lancement.getStrucProcess().getEtape()).build();
    }

    /**
     * Returns the states of the outputs as a plain text response.
     *
     * @return the states of the outputs
     */
    @GET
    @Path("/strucprocess")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lireStrucProcess() {
        StringBuilder sb = new StringBuilder();
        appendStates(sb, "Etape", LANCEMENT.getStrucProcess().getEtape());
        appendStates(sb, "Niveau_Remplissage_Min", LANCEMENT.getStrucProcess().getNiveauRemplissageMin());
        appendStates(sb, "Niveau_Remplissage_Max", LANCEMENT.getStrucProcess().getNiveauRemplissageMax());
        appendStates(sb, "Poids_Minimum", LANCEMENT.getStrucProcess().getPoidsMinimum());
        appendStates(sb, "Poids_Maximum", LANCEMENT.getStrucProcess().getPoidsMaximum());
        return Response.ok(sb.toString()).build();
    }

    /**
     * Ajoute un état à une chaîne de caractères en formatant l'étiquette et la valeur de l'état.
     *
     * @param sb    la chaîne de caractères à laquelle l'état sera ajouté.
     * @param label l'étiquette de l'état.
     * @param state l'état à ajouter à la chaîne de caractères.
     */
    private void appendStates(StringBuilder sb, String label, Object state) {
        if (sb.length() > 0) {
            sb.append(" - ");
        }
        sb.append(label).append(" : ").append(state);
    }

}

