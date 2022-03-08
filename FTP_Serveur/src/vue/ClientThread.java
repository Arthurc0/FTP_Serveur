package vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread implements Runnable {
	

	@Override
	public void run(){
		
		Thread.currentThread().setName(String.valueOf(Client.idClient));
		Client.idClient++;

		Client client = MainServeur.clients.get(Integer.parseInt(Thread.currentThread().getName()));
		
		try{
			PrintStream ps = new PrintStream(client.socket.getOutputStream());
			
			ps.println("1 Bienvenue ! ");
			ps.println("1 Serveur FTP Personnel.");
			ps.println("0 Authentification : ");
			
			String commande = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
			
			// Attente de réception de commandes et leur exécution
			while(!(commande = br.readLine()).equals("bye")) {
				client = MainServeur.clients.get(Integer.parseInt(Thread.currentThread().getName()));
				System.out.println("Client " + client.getId() + " " + client.getUser() + " >> " + commande);
				CommandExecutor.executeCommande(ps, commande);
			}
			System.out.println("Arrêt de la communication avec le client " + client.getId() + ".");

		} catch (SocketException e) {
			System.out.println("Le client " + client.getId() + " s'est déconnecté prématurément");
			try {
				client.socket.close();
			} catch (IOException e1) {}
		} catch (IOException e) {
			System.out.println("Le client " + client.getId() + " s'est déconnecté prématurément");
			try {
				client.socket.close();
			} catch (IOException e1) {}
		}
		
	}
	
}
