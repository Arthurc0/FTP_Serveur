package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeUSER extends Commande {

	public CommandeUSER(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		Client client = Traitement.getClient();
		
		if(commandeArgs.length != 1) {
			ps.println("2 La commande user attend 2 arguments : user <nom>");
		} else {
			File[] fichiers = new File(client.getDossierRacine()).listFiles();
			if(fichiers == null || fichiers.length == 0) {
	    		ps.println("2 Aucun compte client n'existe sur le serveur FTP pour le moment");
			} else {
				// Si le nom d'utilisateur existe
				if(new File(client.getDossierRacine() + "/" + commandeArgs[0]).exists() && new File(client.getDossierRacine() + "/" + commandeArgs[0]).isDirectory()) {
					client.setUserOk(true);
					client.setUser(commandeArgs[0]);
					client.setDossierRacine(client.getDossierRacine() + "/" + commandeArgs[0]);
					client.setDossierCourant(client.getDossierRacine());
					ps.println("0 Commande user OK");
				} else {
					client.setUser("");
					ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
				}
			}
		}
	}
}