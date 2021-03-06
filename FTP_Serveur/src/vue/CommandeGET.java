package vue;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeGET extends Commande {
	
	public CommandeGET(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		Client client = Traitement.getClient();
		
		Traitement.numPortTransferts++;
		
		try {
			// Récupération du contenu du fichier
			BufferedInputStream contenuFichier = new BufferedInputStream(new FileInputStream(client.getDossierCourant() + "/" + commandeArgs[0]));
			
			ServerSocket serveurSTOR = new ServerSocket(Traitement.numPortTransferts);
			
			ps.println("1 Le fichier " + commandeArgs[0] + " existe et prêt pour la réception sur le port");
			ps.println("0 " + Traitement.numPortTransferts);
			
			Socket socketSTOR = serveurSTOR.accept();
			
			byte[] buffer  = new byte[4*1024];
			
			OutputStream contenuSocket = socketSTOR.getOutputStream();
			int nbOctetsLus = -1;
			
			while((nbOctetsLus = contenuFichier.read(buffer, 0, buffer.length)) > -1) {
				contenuSocket.write(buffer, 0, nbOctetsLus);
			}
			
			contenuSocket.close();
			contenuFichier.close();
			socketSTOR.close();
			serveurSTOR.close();
			
			System.out.println("Le fichier " + commandeArgs[0] + " a été téléchargé par le client");
		} catch (FileNotFoundException e) {
			ps.println("2 Le fichier " + commandeArgs[0] + " n'a pas été trouvé par le serveur");
		} catch (IOException e) {
			ps.println("2 Une erreur s'est produite");
		}
	}

}