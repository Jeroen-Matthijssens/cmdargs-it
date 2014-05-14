package it.tutske.cmdargs;

import org.tutske.files.ReaderFactories;


public class CommandRunner {

	public static String run (String ... command) throws Exception {
		Process copy = Runtime.getRuntime ().exec (command);
		copy.waitFor ();

		String err = ReaderFactories.getStringReaderFactory ()
			.newInstance (copy.getErrorStream ())
			.read ();

		if ( err.length () > 0 ) { System.out.println (err); }

		return ReaderFactories.getStringReaderFactory ()
			.newInstance (copy.getInputStream ())
			.read ();
	}

}
