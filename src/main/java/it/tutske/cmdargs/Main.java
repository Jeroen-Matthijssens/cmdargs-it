package it.tutske.cmdargs;

import java.util.List;

import org.tutske.cmdargs.*;
import org.tutske.cmdargs.exceptions.*;


public class Main {

	private ParsedCommand parsed;

	private Main (ParsedCommand parsed) {
		this.parsed = parsed;
	}

	public static void main (String [] args) {
		Parser parser = ParserFactory.newInstance (Options.scheme);

		try { new Main (parser.parse (args)).run (); }
		catch (CommandLineException exception) { parser.printError (); }
	}

	public void run () {
		if ( parsed.hasOption (Options.printAll) ) { printAll (); }
		else if ( ! parsed.hasOption (Options.print) ) { return; }
		else if ( parsed.getOptionValue (Options.print)) { printFirst (); }
	}

	public void printAll () {
		List<String> messages = parsed.getOptionValues (Options.msg);
		for ( String message : messages ) { printSingle (message); }
	}

	public void printFirst () {
		String message = parsed.getOptionValue (Options.msg);
		printSingle (message);
	}

	public void printSingle (String msg) {
		System.out.println (msg);
	}

}
