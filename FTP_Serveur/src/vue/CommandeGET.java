package vue;

import java.io.PrintStream;

public class CommandeGET extends Commande {
	
	public CommandeGET(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		ps.println("2 La commande get n'est pas encore implémentée");
	}

}