package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeUSER extends Commande {
	
	public CommandeUSER(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		if(commandeArgs.length != 1) {
			ps.println("2 La commande USER attend 2 arguments : USER <nom>");
		} else {
			File[] fichiers = new File(Traitement.dossierRacine).listFiles();
			if(fichiers == null || fichiers.length == 0) {
	    		ps.println("2 Aucun compte client n'existe sur le serveur FTP pour le moment");
			} else {
				// Si le nom d'utilisateur existe
				if(new File(Traitement.dossierRacine + "/" + commandeArgs[0]).exists() && new File(Traitement.dossierRacine + "/" + commandeArgs[0]).isDirectory()) {
					CommandExecutor.userOk = true;
					Traitement.dossierRacine += "/" + commandeArgs[0];
					Traitement.dossierCourant = Traitement.dossierRacine;
					ps.println("0 Commande user OK");
				} else {
					ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
				}
			}
		}
	}

}