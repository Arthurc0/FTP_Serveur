package vue;

import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		Client client = Traitement.getClient();
		
		// Traiter l'argument s'il existe
		if(commandeArgs.length > 0) commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
		
		// S'il n'y a aucun argument ou le premier argument est vide
		if(commandeArgs.length == 0 || commandeArgs[0].length() == 0) {
			client.setDossierCourant(client.getDossierRacine());
			client.setTmpChemin(client.getDossierCourant());
    		ps.println("0 Dossier courant modifié : " + client.getDossierCourant());
		} else {
			if(Traitement.cheminExiste(ps, commandeArgs[0])) {
				client.setDossierCourant(client.getTmpChemin());
				ps.println("0 Dossier courant modifié : " + client.getDossierCourant());
			}
		}
	}
}