package vue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MainServeur {
	
	public static HashMap<Integer, Client> clients = new HashMap<Integer, Client>();
	
	public static void main(String[] args) throws IOException {
		System.out.println("Le Serveur FTP");
		
		@SuppressWarnings("resource")
		ServerSocket serveurFTP = new ServerSocket(2121);
		
		while(true) {
			System.out.println("Attente de connexion d'un client...");
			Socket socket = serveurFTP.accept();
			
			Client client = new Client();
			client.setSocket(socket);
			
			clients.put(client.getId(), client);
			
			System.out.println("Client n°" + client.getId() + " connecté");
			
			Thread clientTh = new Thread(new ClientThread());
			clientTh.start();
		}
		
	}
}