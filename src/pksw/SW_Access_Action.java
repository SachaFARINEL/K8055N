package pksw;

import pkprocess.Lancement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/k8055action")
public class SW_Access_Action {
	
	//Cr�ation de l'objet Lancement (1 seule instance possible par Carte)
	//.
	//.
	//.
	protected static Lancement Lanceur;
	
	public SW_Access_Action()
	{
		this.Lanceur = new Lancement();
	}
	
	// Lancement du process (Version navigateur)
	@GET
	@Path("/lance")
	@Produces(MediaType.TEXT_PLAIN)
	public String LanceProcess() {
		
		if (!Lanceur.lance())
			return "Process Lance";
		else
			return "Process en cours";	
	}
	
	// Arr�t du process (Version navigateur)
	@GET
	@Path("/arret")
	@Produces(MediaType.TEXT_PLAIN)
	public String ArretProcess() {
		Lanceur.arret();
		return "Process Arrete";
	}
	
	// RAZ du process (Version navigateur)
	@GET
	@Path("/raz")
	@Produces(MediaType.TEXT_PLAIN)
	public String RazProcess() {
		Lanceur.raz();
		return "Raz Process OK";
	}

}
