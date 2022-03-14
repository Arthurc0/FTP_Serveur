package vue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

public class CommandePASS extends Commande {

	public CommandePASS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		Client client = Traitement.getClient();
		
		if(commandeArgs.length != 1) {
			ps.println("2 La commande pass attend 2 arguments : pass <mot_de_passe>");
		} else {
			// Si le dossier du client possède un fichier pw.txt
			if(new File(client.getDossierRacine() + "/pw.txt").exists() && new File(client.getDossierRacine() + "/pw.txt").isFile()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(client.getDossierRacine() + "/pw.txt")); 
					String mdp = br.readLine();
					br.close();
					
					// Si les mots de passe correspondent
					if(commandeArgs[0].equals(mdp)) {
						client.setPwOk(true);
						ps.println("1 Commande pass OK");
						ps.println("0 Vous êtes bien connecté sur notre serveur");
					} else {
						client.setUser("");
						client.setUserOk(false);
						client.setDossierCourant(client.getDossierCourant().substring(0, client.getDossierCourant().length() - (client.getDossierCourant().length() - client.getDossierCourant().lastIndexOf("/"))));
						client.setDossierRacine(client.getDossierCourant());
						ps.println("2 Le mot de passe est faux");
					}
				} catch(Exception e) {
					ps.println("2 Une erreur s'est produite");
				}
			} else {
				ps.println("2 Impossible de se connecter car aucun mot de passe n'est associé à ce compte !");
			}
		}
	}
}