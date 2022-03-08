package vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class MainServeur {

	public static HashMap<Integer, Client> clients = new HashMap<Integer, Client>();
	
	public static void main(String[] args) throws IOException{
		System.out.println("Le Serveur FTP");
		
		while(true) {
			ServerSocket serveurFTP = new ServerSocket(2121);
			System.out.println("Attente de connexion d'un client...");
			Socket socket = serveurFTP.accept();
			
			Client client = new Client();
			client.socket = socket;
			
			clients.put(client.getId(), client);
			
			System.out.println("Client n°" + client.getId() + " connecté");
			
			Thread clientTh = new Thread(new ClientThread());
			//clientTh.setName(String.valueOf(client.getId()));
			
			
			clientTh.start();

			
			serveurFTP.close();
		}
		
	}
}