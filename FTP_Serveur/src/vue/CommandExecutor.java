package vue;

import java.io.PrintStream;

public class CommandExecutor {
	
	public static boolean userOk = false;
	public static boolean pwOk = false;
	
	public static void executeCommande(PrintStream ps, String commande) {
		if(userOk && pwOk) {
			// Changer de r�pertoire. Un (..) permet de revenir au r�pertoire sup�rieur
			if(commande.split(" ")[0].equals("cd")) (new CommandeCD(ps, commande)).execute();
	
			// T�l�charger un fichier
			if(commande.split(" ")[0].equals("get")) (new CommandeGET(ps, commande)).execute();
			
			// Afficher la liste des fichiers et des dossiers du r�pertoire courant
			if(commande.split(" ")[0].equals("ls")) (new CommandeLS(ps, commande)).execute();
		
			// Afficher le r�pertoire courant
			if(commande.split(" ")[0].equals("pwd")) (new CommandePWD(ps, commande)).execute();
			
			// Envoyer (uploader) un fichier
			if(commande.split(" ")[0].equals("stor")) (new CommandeSTOR(ps, commande)).execute();
		}
		else {
			if(commande.split(" ")[0].equals("pass") || commande.split(" ")[0].equals("user")) {
				// Le mot de passe pour l'authentification
				if(commande.split(" ")[0].equals("pass")) (new CommandePASS(ps, commande)).execute();
	
				// Le login pour l'authentification
				if(commande.split(" ")[0].equals("user")) (new CommandeUSER(ps, commande)).execute();
			}
			else
				ps.println("2 Vous n'�tes pas connect� !");
		}
	}
}