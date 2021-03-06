package vue;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;

public class CommandeRMDIR extends Commande {

	public CommandeRMDIR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		Client client = Traitement.getClient();
		
		if(commandeArgs.length != 1) {
			ps.println("2 La commande rmdir attend 2 arguments : rmdir <dossier>");
		} else {
			commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
			
			if(Traitement.verifDossier(ps, commandeArgs[0], 0)) {
				new File(client.getTmpChemin()).delete();
				ps.println("0 Le dossier " + new File(client.getTmpChemin()).getName() + " a été supprimé");
				//Si le dossier courant est supprimé
				if(client.getTmpChemin().equals(client.getDossierCourant())) {
					client.setDossierCourant(client.getDossierCourant().substring(0, client.getDossierCourant().length() - (client.getDossierCourant().length() - client.getDossierCourant().lastIndexOf("/"))));
				}
			}
		}
	}
}