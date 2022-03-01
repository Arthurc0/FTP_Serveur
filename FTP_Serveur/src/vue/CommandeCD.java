package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	// Supprime les caractères "/" en doublon et autour de la chaines
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

	public void execute() {
		
		// Traiter l'argument s'il existe
		if(commandeArgs.length > 0) commandeArgs[0] = trimSlashes(commandeArgs[0]);
		
		// S'il n'y a aucun argument ou le premier argument est vide
		if(commandeArgs.length == 0 || commandeArgs[0].length() == 0) {
			Traitement.dossierCourant = "root";
			Traitement.tmpChemin = Traitement.dossierCourant;
    		ps.println("0 Dossier courant modifié : " + Traitement.dossierCourant);
		} else {
			boolean cheminExiste = false;
			
			cheminExiste = Traitement.cheminExiste(ps, commandeArgs[0]);
			
			if(cheminExiste) {
				Traitement.dossierCourant = Traitement.tmpChemin;
				ps.println("0 Dossier courant modifié : " + Traitement.dossierCourant);
			}
		}
	}
}