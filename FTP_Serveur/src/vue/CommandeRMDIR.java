package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeRMDIR extends Commande {
	public CommandeRMDIR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		if(commandeArgs.length != 1) {
			ps.println("2 La commande rmdir attend 2 arguments : rmdir <dossier>");
		} else {
			commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
			
			if(Traitement.verifDossier(ps, commandeArgs[0], 0)) {
				new File(Traitement.tmpChemin).delete();
				ps.println("0 Le dossier " + new File(Traitement.tmpChemin).getName() + " a été supprimé");
			}
		}
	}
}