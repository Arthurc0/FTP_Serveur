package vue;

import java.net.Socket;

public class Client {

	public static int idClient = 1;
	
	private int id;
	private String user;
	private boolean userOk;
	private boolean pwOk;
	private Socket socket;
	private String dossierCourant;
	private String tmpChemin;
	private String dossierRacine;
	
	public Client() {
		this.id = idClient;
		idClient++;
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
	
	public void setUser(String user) {
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getDossierCourant() {
		return dossierCourant;
	}

	public void setDossierCourant(String dossierCourant) {
		this.dossierCourant = dossierCourant;
	}

	public String getTmpChemin() {
		return tmpChemin;
	}

	public void setTmpChemin(String tmpChemin) {
		this.tmpChemin = tmpChemin;
	}

	public String getDossierRacine() {
		return dossierRacine;
	}

	public void setDossierRacine(String dossierRacine) {
		this.dossierRacine = dossierRacine;
	}
}