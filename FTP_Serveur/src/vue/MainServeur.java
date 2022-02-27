package vue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServeur {
	
	public static String dossierCourant = "root";

	public static void main(String[] args) throws Exception {
		System.out.println("Le Serveur FTP");
		
		ServerSocket serveurFTP = new ServerSocket(2121);
		Socket socket = serveurFTP.accept();
		
		PrintStream ps = new PrintStream(socket.getOutputStream());
		
		ps.println("1 Bienvenue ! ");
		ps.println("1 Serveur FTP Personnel.");
		ps.println("0 Authentification : ");
		
		String commande = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// Attente de réception de commandes et leur exécution
		while(!(commande = br.readLine()).equals("bye")) {
			System.out.println(">> " + commande);
			CommandExecutor.executeCommande(ps, commande);
		}
		System.out.println("Arrêt de la communication avec le client FTP.");
		
		serveurFTP.close();
		socket.close();
	}
}