package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeRMDIR extends Commande {

	public CommandeRMDIR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		Client client = MainServeur.clients.get(Integer.parseInt(Thread.currentThread().getName()));
		
		if(commandeArgs.length != 1) {
			ps.println("2 La commande rmdir attend 2 arguments : rmdir <dossier>");
		} else {
			commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
			
			if(Traitement.verifDossier(ps, commandeArgs[0], 0)) {
				new File(client.tmpChemin).delete();
				ps.println("0 Le dossier " + new File(client.tmpChemin).getName() + " a été supprimé");
			}
		}
	}
}