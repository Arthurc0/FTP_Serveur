package vue;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeSTOR extends Commande {
	
	public CommandeSTOR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		Traitement.numPortTransferts++;
		
		try {
			// Création du fichier et récupération de son contenu
			BufferedOutputStream contenuFichier = new BufferedOutputStream(new FileOutputStream(Traitement.dossierCourant + "/" + commandeArgs[0]));
			
			ServerSocket serveurSTOR = new ServerSocket(Traitement.numPortTransferts);
			
			ps.println("1 Le fichier " + commandeArgs[0] + " existe et prêt pour la réception sur le port");
			ps.println("0 " + Traitement.numPortTransferts);
			
			Socket socketSTOR = serveurSTOR.accept();
			
			byte[] buffer  = new byte[4*1024];
			
			InputStream contenuSocket = socketSTOR.getInputStream();
			int nbOctetsLus = -1;
			
			while((nbOctetsLus = contenuSocket.read(buffer, 0, buffer.length)) > -1) {
				contenuFichier.write(buffer, 0, nbOctetsLus);
			}
			
			contenuSocket.close();
			contenuFichier.close();
			socketSTOR.close();
			serveurSTOR.close();
			
			System.out.println("Le fichier " + commandeArgs[0] + " a été créé dans le dossier " + Traitement.dossierCourant + " par le client");
		} catch (FileNotFoundException e) {
			ps.println("2 Le fichier " + commandeArgs[0] + " n'a pas pu être créé par le serveur");
		} catch (IOException e) {
			
		}
	}

}