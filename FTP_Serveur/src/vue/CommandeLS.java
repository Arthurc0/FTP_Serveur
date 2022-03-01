package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeLS extends Commande {
	
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	// Supprime les caractères "/" en doublon et autour de la chaine
	private String trimSlashes(String str) {
		// doublons
	    str = str.replaceAll("\\/+", "/");
		
		// "/" de fin
		if(str.endsWith("/"))
			str = str.substring(0, str.length()-1);
		
		// "/" de début
		if(str.startsWith("/"))
			str = str.substring(1, str.length());
		
		return str;
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
		// Traiter l'argument s'il existe
		if(commandeArgs.length > 0) commandeArgs[0] = trimSlashes(commandeArgs[0]);
		
		// S'il n'y a aucun argument ou le premier argument est vide
		if(commandeArgs.length == 0 || commandeArgs[0].length() == 0) {
    		ps.println(printLS(Traitement.dossierCourant));
		}else {
			boolean cheminExiste = false;
			
			cheminExiste = Traitement.cheminExiste(ps, commandeArgs[0]);
			
			if(cheminExiste) {
				ps.println(printLS(Traitement.tmpChemin));
			}
		}
		
	}

}