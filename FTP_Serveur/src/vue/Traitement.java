package vue;

import java.io.File;
import java.io.PrintStream;

public class Traitement {

	public static String dossierCourant = "root";
	public static String tmpChemin = "";
	public static String dossierRacine = "root";
	
	public static int numPortTransferts = 4000;
	
	// Supprime les caractères "/" en doublon et autour de la chaine
	public static String trimSlashes(String str) {
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
	
	public static boolean cheminExiste(PrintStream ps, String nomDossier) {
		boolean existe = false;
		tmpChemin = dossierCourant;
		
		for(String s : nomDossier.split("/")) {
			// Si la chaine est ".." (accès au répertoire supérieur)
			if(s.equals("..")) {
				if(tmpChemin.equals(dossierRacine)) {
					ps.println("2 Vous ne pouvez pas accéder à un répertoire supérieur à votre dossier personnel");
		    		existe = false;
					break;
				} else {
					tmpChemin = tmpChemin.substring(0, tmpChemin.lastIndexOf("/"));
					existe = true;
				}
			// Si la chaine comporte exclusivement 1 ou au moins 3 "."
			} else if(s.matches("^[.]{1}$|^[.]{3,}$")) {
				existe = true;
			// Si la chaine est un nom de dossier
			} else {
				File[] fichiers = new File(tmpChemin).listFiles();
				if(fichiers == null || fichiers.length == 0) {
		    		ps.println("2 Le dossier " + new File(tmpChemin).getName() + " ne contient aucun dossier");
		    		existe = false;
		    		break;
				} else {
					tmpChemin += "/" + s;
					if(new File(tmpChemin).exists() && new File(tmpChemin).isDirectory()) {
						existe = true;
					} else {
						ps.println("2 Le dossier " + s + " n'existe pas");
						existe = false;
						break;
					}
				}
			}
		}
		
		return existe;
	}
}
