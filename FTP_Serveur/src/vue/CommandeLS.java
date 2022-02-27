package vue;

import java.io.PrintStream;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		ps.println("2 La commande ls n'est pas encore implémentée");
	}

}