package it.tutske.cmdargs;

import org.tutske.cmdargs.*;


public class Options {

	public static final ValueOption<String> msg = new StringOption ("message", "m", true);
	public static final ValueOption<Boolean> print = new BooleanOption ("print", "p");
	public static final Option printAll = new BasicOption ("print all", "P");

	// Uses above values, so this has to go after the options definitions
	public static final CommandScheme scheme = Options.createScheme ();

	private static CommandScheme createScheme () {
		return CommandSchemeBuilderFactory.newInstance ()
			.addOption (Options.printAll)
			.addOption (Options.print)
			.addOption (Options.msg)
			.buildScheme ();
	}

}
