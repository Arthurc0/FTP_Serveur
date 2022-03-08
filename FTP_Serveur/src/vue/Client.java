package vue;

import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	public static int idClient = 1;
	
	private int id;
	private String user;
	private boolean userOk;
	private boolean pwOk;
	public Socket socket;
	public String dossierCourant;
	public String tmpChemin;
	public String dossierRacine;
	
	public Client() {
		this.id = idClient;
		this.user = "";
		this.userOk = false;
		this.pwOk = false;		
		this.dossierCourant = "root";
		this.dossierRacine = "root";
		this.tmpChemin = "";
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String name) {
		this.user = user;
	}

	public boolean getUserOk() {
		return this.userOk;
	}
	
	public void setUserOk(boolean userOk) {
		this.userOk = userOk;
	}
	
	public boolean getPwOk() {
		return this.pwOk;
	}
	
	public void setPwOk(boolean pwOk) {
		this.pwOk = pwOk;
	}
	
	

}
