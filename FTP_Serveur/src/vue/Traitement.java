package vue;

import java.io.File;
import java.io.PrintStream;

public class Traitement {
	public static int numPortTransferts = 4000;
	
	public static Client getClient() {
		return MainServeur.clients.get(Integer.parseInt(Thread.currentThread().getName()));
	}
	
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
	
	// Indique si le chemin existe (pour les commandes CD et LS)
	public static boolean cheminExiste(PrintStream ps, String chemin) {
		Client client = getClient();
		
		boolean existe = false;
		client.setTmpChemin(client.getDossierCourant());
		
		for(String s : chemin.split("/")) {
			// Si la chaine est ".." (accès au répertoire supérieur)
			if(s.equals("..")) {
				if(client.getTmpChemin().equals(client.getDossierRacine())) {
					ps.println("2 Vous ne pouvez pas accéder à un répertoire supérieur à votre dossier personnel");
		    		existe = false;
					break;
				} else {
					client.setTmpChemin(client.getTmpChemin().substring(0, client.getTmpChemin().lastIndexOf("/")));
					existe = true;
				}
			}
			// Si la chaine comporte exclusivement 1 ou au moins 3 "."
			else if(s.matches("^[.]{1}$|^[.]{3,}$")) {
				existe = true;
			}
			// Si la chaine est un nom de dossier
			else {
				File[] fichiers = new File(client.getTmpChemin()).listFiles();
				
				if(fichiers == null || fichiers.length == 0) {
		    		ps.println("2 Le dossier " + new File(client.getTmpChemin()).getName() + " ne contient aucun dossier");
		    		existe = false;
		    		break;
				} else {
					client.setTmpChemin(client.getTmpChemin() + "/" + s);
					if(new File(client.getTmpChemin()).exists() && new File(client.getTmpChemin()).isDirectory()) {
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
	
	/*
	 * Indique si le dossier :
	 *  peut être créé (pour la commande MKDIR) -> methode = 1
	 *  peut être supprimé (pour la commande RMDIR) -> methode = 0
	 */
	public static boolean verifDossier(PrintStream ps, String dossiers, int methode) {
		Client client = getClient();
		
		boolean verifCreer = false;
		boolean verifSupprimer = false;
		
		client.setTmpChemin(client.getDossierCourant());
		
		String[] chemin = dossiers.split("/");
		int positionDossier = 0;
		
		for(String s : chemin) {
			// Si la chaine est ".." (accès au répertoire supérieur)
			if(s.equals("..")) {
				// Si la chaine est la dernière du chemin (son nom est donc invalide)
				if(positionDossier == chemin.length -1) {
					ps.println("2 Le nom du dossier n'est pas renseigné");
					verifCreer = false;
					verifSupprimer = false;
					break;
				} else {
					if(client.getTmpChemin().equals(client.getDossierRacine())) {
						ps.println("2 Vous ne pouvez pas accéder à un répertoire supérieur à votre dossier personnel");
						verifCreer = false;
						verifSupprimer = false;
						break;
					} else {
						client.setTmpChemin(client.getTmpChemin().substring(0, client.getTmpChemin().lastIndexOf("/")));
						positionDossier++;
					}
				}
			}
			// Si la chaine comporte exclusivement 1 ou au moins 3 ".",
			// ou si elle contient au moins 1 caractère non autorisé
			else if(s.matches("^[.]{1}$|^[.]{3,}$") || !s.matches("[^\\/:*?\"<>|]+")) {
				ps.println("2 Le nom de dossier " + s + " est incorrect");
				verifCreer = false;
				verifSupprimer = false;
				break;
			}
			// Si la chaine est un nom de dossier
			else {
				// Si le nom de dossier n'est pas le dernier du chemin
				if(positionDossier != chemin.length - 1) {
					File[] fichiers = new File(client.getTmpChemin()).listFiles();
					
					if(fichiers == null || fichiers.length == 0) {
			    		ps.println("2 Le dossier " + new File(client.getTmpChemin()).getName() + " ne contient aucun dossier");
			    		verifCreer = false;
						verifSupprimer = false;
			    		break;
					} else {
						client.setTmpChemin(client.getTmpChemin() + "/" + s);
						positionDossier++;
						// Si le dossier n'existe pas
						if(!new File(client.getTmpChemin()).exists() || !new File(client.getTmpChemin()).isDirectory()) {
							ps.println("2 Le dossier " + s + " n'existe pas");
							verifCreer = false;
							verifSupprimer = false;
							break;
						}
					}
				} else {
					client.setTmpChemin(client.getTmpChemin() + "/" + s);
					File dossier = new File(client.getTmpChemin());
					
					if(methode == 1) {
						if(dossier.exists()) {
							ps.println("2 Le nom " + s + " existe déjà");
							verifCreer = false;
						} else
							verifCreer = true;
					} else {
						if(dossier.exists() && dossier.isDirectory()) {
							if(dossier.listFiles().length == 0)
								verifSupprimer = true;
							else {
								ps.println("2 Le dossier " + s + " n'est pas vide");
								verifSupprimer = false;
							}
						} else {
							ps.println("2 Le dossier " + s + " n'existe pas");
							verifSupprimer = false;
						}
					}
					
					break;
				}
			}
		}
		
		if(methode == 1) return verifCreer;
		else return verifSupprimer;
	}
}
