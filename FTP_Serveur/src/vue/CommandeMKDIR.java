package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeMKDIR extends Commande {
	public CommandeMKDIR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		if(commandeArgs.length != 1) {
			ps.println("2 La commande mkdir attend 2 arguments : mkdir <dossier>");
		} else {
			commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
			
			// Si le nom du dossier demandé existe déjà
			if(Traitement.verifDossier(ps, commandeArgs[0], 1)) {
				new File(Traitement.tmpChemin).mkdir();
				ps.println("0 Le dossier " + new File(Traitement.tmpChemin).getName() + " a été créé");
			}
		}
	}
}