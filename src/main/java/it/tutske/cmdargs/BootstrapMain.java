package it.tutske.cmdargs;

import java.io.*;


public class BootstrapMain {

	public static void main (String [] args) throws Exception {
		File file = new File ("./target/dependency/");

		if ( ! file.exists () ) {
			System.out.println ("no such dir");
			copyDependencies ();
		}
		else if ( ! file.isDirectory () ) {}
		else if ( file.listFiles ().length == 0 ) {
			System.out.println ("no files in dir");
			copyDependencies ();
		}

		TestMain.main (args);
	}

	private static void copyDependencies () throws Exception {
		String command = "mvn dependency:copy-dependencies";
		System.out.println ("$ " + command);
		String out = CommandRunner.run (command);
		System.out.println (out);
	}

}
