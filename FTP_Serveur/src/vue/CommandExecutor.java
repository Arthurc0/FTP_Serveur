package vue;

import java.io.PrintStream;

public class CommandExecutor {
	
	public static boolean commandeExiste = false;
	
	public static void executeCommande(PrintStream ps, String commande) {
		Client client = Traitement.getClient();
		
		if(client.getUserOk() && client.getPwOk()) {
			commandeExiste = false;
			
			// Changer de répertoire. Un (..) permet de revenir au répertoire supérieur
			if(commande.split(" ")[0].equals("cd")) (new CommandeCD(ps, commande)).execute();
			
			// Télécharger un fichier
			if(commande.split(" ")[0].equals("get")) (new CommandeGET(ps, commande)).execute();
			
			// Afficher la liste des fichiers et des dossiers du répertoire courant
			if(commande.split(" ")[0].equals("ls")) (new CommandeLS(ps, commande)).execute();
			
			// Afficher le répertoire courant
			if(commande.split(" ")[0].equals("pwd")) (new CommandePWD(ps, commande)).execute();
			
			// Envoyer (uploader) un fichier
			if(commande.split(" ")[0].equals("stor")) (new CommandeSTOR(ps, commande)).execute();
			
			// Créer un nouveau dossier dans le répertoire courant s'il est inexistant
			if(commande.split(" ")[0].equals("mkdir")) (new CommandeMKDIR(ps, commande)).execute();
			
			// Supprimer un dossier existant dans le répertoire courant s'il est vide
			if(commande.split(" ")[0].equals("rmdir")) (new CommandeRMDIR(ps, commande)).execute();
			
			// Si la commande reçue n'existe pas
			if(!commandeExiste) ps.println("2 La commande n'existe pas");
		}
		else {
			if(commande.split(" ")[0].equals("pass") || commande.split(" ")[0].equals("user")) {
				// Le login pour l'authentification
				if(commande.split(" ")[0].equals("user")) {
					if(!client.getUserOk())
						(new CommandeUSER(ps, commande)).execute();
					else
						ps.println("2 Vous avez déjà entré le login !");
				}
				
				// Le mot de passe pour l'authentification
				if(commande.split(" ")[0].equals("pass")) {
					if(client.getUserOk())
						(new CommandePASS(ps, commande)).execute();
					else
						ps.println("2 Vous n'avez pas entré le login !");
				}
			}
			else
				ps.println("2 Vous n'êtes pas connecté !");
		}
	}
}