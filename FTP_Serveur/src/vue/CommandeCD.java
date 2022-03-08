package vue;

import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}


	public void execute() {
		Client client = MainServeur.clients.get(Integer.parseInt(Thread.currentThread().getName()));
		
		// Traiter l'argument s'il existe
		if(commandeArgs.length > 0) commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
		
		// S'il n'y a aucun argument ou le premier argument est vide
		if(commandeArgs.length == 0 || commandeArgs[0].length() == 0) {
			client.dossierCourant = client.dossierRacine;
			client.tmpChemin = client.dossierCourant;
    		ps.println("0 Dossier courant modifié : " + client.dossierCourant);
		} else {
			if(Traitement.cheminExiste(ps, commandeArgs[0])) {
				client.dossierCourant = client.tmpChemin;
				ps.println("0 Dossier courant modifié : " + client.dossierCourant);
			}
		}
	}
}