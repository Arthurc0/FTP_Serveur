package vue;

import java.io.PrintStream;

public class CommandePWD extends Commande {

	public CommandePWD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		Client client = MainServeur.clients.get(Integer.parseInt(Thread.currentThread().getName()));
		
		ps.println("0 " + client.dossierCourant);
	}

}