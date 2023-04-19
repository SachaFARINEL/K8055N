package pksw;

import pkprocess.Lancement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Cette classe implémente les actions liées à l'accès au SW_K8055.
 * Elle permet notamment de lancer, arrêter et réinitialiser le processus de communication avec le SW_K8055.
 */
@Path("/k8055action")
public class SW_Access_Action {
    /**
     * Instance de la classe Lancement permettant de lancer et arrêter le processus de communication avec le SW_K8055.
     */
    protected static Lancement lancement = new Lancement();

    /**
     * Cette méthode permet de lancer le processus de communication avec le SW_K8055.
     * @return Response objet représentant la réponse HTTP retournée.
     * Si le lancement s'est bien déroulé, la réponse contient le message "Process lancé" avec un code HTTP 200 OK.
     * Si le lancement a échoué, la réponse contient le message "Erreur lors du lancement du process" avec un code HTTP 500 Internal Server Error.
     */
    @GET
    @Path("/lance")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lanceProcess() {
        if (lancement.lance()) {
            return Response.ok("Process lancé").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors du lancement du process").build();
        }
    }

    /**
     * Cette méthode permet d'arrêter le processus de communication avec le SW_K8055.
     * @return Response objet représentant la réponse HTTP retournée.
     * La réponse contient le message "Process arrêté" avec un code HTTP 200 OK.
     */
    @GET
    @Path("/arret")
    @Produces(MediaType.TEXT_PLAIN)
    public Response arretProcess() {
        lancement.arret();
        return Response.ok("Process arrêté").build();
    }

    /**
     * Cette méthode permet de réinitialiser le processus de communication avec le SW_K8055.
     * @return Response objet représentant la réponse HTTP retournée.
     * La réponse contient le message "Raz Process OK" avec un code HTTP 200 OK.
     */
    @GET
    @Path("/raz")
    @Produces(MediaType.TEXT_PLAIN)
    public Response razProcess() {
        lancement.raz();
        return Response.ok("Raz Process OK").build();
    }

}
