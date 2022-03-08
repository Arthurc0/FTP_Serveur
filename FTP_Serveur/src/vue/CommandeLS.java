package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeLS extends Commande {

	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	// Génere la chaine de LS
	private String printLS(String chemin) {
		File[] fichiers = new File(chemin).listFiles();
		String currDir = new File(chemin).getName();

		String str = "0 " + currDir + ":";
		
		for(File f : fichiers) {
			if(f.isFile())
				str = str + "   (f)" + f.getName();
			else
				str = str + "   (d)" + f.getName();
		}
		
		return str;
	}
	
	public void execute() {
		Client client = Traitement.getClient();
		
		// Traiter l'argument s'il existe
		if(commandeArgs.length > 0) commandeArgs[0] = Traitement.trimSlashes(commandeArgs[0]);
		
		// S'il n'y a aucun argument ou le premier argument est vide
		if(commandeArgs.length == 0 || commandeArgs[0].length() == 0) {
    		ps.println(printLS(client.getDossierCourant()));
		} else {
			if(Traitement.cheminExiste(ps, commandeArgs[0])) {
				ps.println(printLS(client.getTmpChemin()));
			}
		}
	}
}