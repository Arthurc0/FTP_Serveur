package vue;

import java.io.File;
import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
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

	public void execute() {
		String tmpChemin = MainServeur.dossierCourant;
		
		// Traiter l'argument s'il existe
		if(commandeArgs.length > 0) commandeArgs[0] = trimSlashes(commandeArgs[0]);
		
		// S'il n'y a aucun argument ou le premier argument est vide
		if(commandeArgs.length == 0 || commandeArgs[0].length() == 0) {
			MainServeur.dossierCourant = "root";
			tmpChemin = MainServeur.dossierCourant;
    		ps.println("0 Dossier courant modifié : " + MainServeur.dossierCourant);
		} else {
			boolean cheminExiste = false;
			
			// Parcours des dossiers en argument
			for(String s : commandeArgs[0].split("/")) {
				// Si la chaine est ".." (accès au répertoire supérieur)
				if(s.equals("..")) {
					if(tmpChemin.equals("root")) {
						ps.println("2 Vous ne pouvez pas accéder à un répertoire supérieur au dossier racine");
			    		cheminExiste = false;
						break;
					} else {
						tmpChemin = tmpChemin.substring(0, tmpChemin.lastIndexOf("/"));
						cheminExiste = true;
					}
				// Si la chaine comporte exclusivement 1 ou au moins 3 "."
				} else if(s.matches("^[.]{1}$|^[.]{3,}$")) {
					cheminExiste = true;
				// Si la chaine est un nom de dossier
				} else {
					File[] fichiers = new File(tmpChemin).listFiles();
					if(fichiers == null || fichiers.length == 0) {
			    		ps.println("2 Le dossier " + new File(tmpChemin).getName() + " ne contient aucun dossier");
			    		cheminExiste = false;
			    		break;
					} else {
						tmpChemin += "/" + s;
						if(new File(tmpChemin).exists() && new File(tmpChemin).isDirectory()) {
							cheminExiste = true;
						} else {
							ps.println("2 Le dossier " + s + " n'existe pas");
							cheminExiste = false;
							break;
						}
					}
				}
			}
			
			if(cheminExiste) {
				MainServeur.dossierCourant = tmpChemin;
				ps.println("0 Dossier courant modifié : " + MainServeur.dossierCourant);
			}
		}
	}
}