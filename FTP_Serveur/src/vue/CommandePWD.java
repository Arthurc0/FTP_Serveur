package vue;

import java.io.PrintStream;

public class CommandePWD extends Commande {

	public CommandePWD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		Client client = Traitement.getClient();
		ps.println("0 " + client.getDossierCourant());
	}
}