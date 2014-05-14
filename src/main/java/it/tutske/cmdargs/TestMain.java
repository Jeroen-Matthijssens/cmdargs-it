package it.tutske.cmdargs;


public class TestMain {

	private static final String CP = "target/cmdargs-it-1.0.0-SNAPSHOT.jar";
	private static final String MAIN = "it.tutske.cmdargs.Main";
	private static final String [] CMD = new String [] {"java", "-cp", CP, MAIN};
	private static final String FAILED = "$ %s\n gave `%s` but should have been `%s`.";

	public static void main (String [] args) throws Exception {
		new TestMain ().run ();
	}

	public void run () {
		testLong ();
		testShort ();
		testMixed ();
		testErrors ();
	}

	private void testLong () {
		test (new String [] {"--message=Hello", "--print"}, "Hello\n");
		test (new String [] {"--print", "--message=Hello World!"}, "Hello World!\n");
		test (new String [] {"--print=true", "--message=Hello World!"}, "Hello World!\n");
		test (new String [] {"--print=true", "--message="}, "\n");

		test (new String [] {"--message=Hello World!", "--no-print"}, "");
		test (new String [] {"--no-print", "--message=Hello World!"}, "");
		test (new String [] {"--print=false", "--message=Hello World!"}, "");

		test (new String [] {"--print", "--message=Hello", "--message=World"}, "Hello\n");
		test (new String [] {"--message=Hello", "--print", "--message=World"}, "Hello\n");
		test (new String [] {"--message=Hello", "--message=World", "--print"}, "Hello\n");

		test (new String [] {"--print-all", "--message=Hello", "--message=World"}, "Hello\nWorld\n");
		test (new String [] {"--message=Hello", "--print-all", "--message=World"}, "Hello\nWorld\n");
		test (new String [] {"--message=Hello", "--message=World", "--print-all"}, "Hello\nWorld\n");
	}

	private void testShort () {
		test (new String [] {"-m", "Hello", "-p"}, "Hello\n");
		test (new String [] {"-m", "Hello", "-P"}, "Hello\n");
		test (new String [] {"-p", "-m", "Hello"}, "Hello\n");
		test (new String [] {"-P", "-m", "Hello"}, "Hello\n");

		test (new String [] {"-m", "Hello", "-p", "-m", "World"}, "Hello\n");
		test (new String [] {"-m", "Hello", "-P", "-m", "World"}, "Hello\nWorld\n");
		test (new String [] {"-p", "-m", "Hello", "-m", "World"}, "Hello\n");
		test (new String [] {"-P", "-m", "Hello", "-m", "World"}, "Hello\nWorld\n");

		test (new String [] {"-m", "Hello"}, "");
		test (new String [] {"-m", "Hello", "-p", "false"}, "");
		test (new String [] {"-m", "Hello", "-m", "World"}, "");

		test (new String [] {"-m", "Hello", "-p", "false", "-m", "World"}, "");
		test (new String [] {"-m", "Hello", "-P", "-m", "World"}, "Hello\nWorld\n");
		test (new String [] {"-p", "false", "-m", "Hello", "-m", "World"}, "");
		test (new String [] {"-P", "-m", "Hello", "-m", "World", "-p", "false"}, "Hello\nWorld\n");
	}

	private void testMixed () {
		
	}

	private void testErrors () {
		String MISSING_OPTION = "Error: missing required option `--message`!\n";
		test ( new String [] {}, MISSING_OPTION);
		test ( new String [] {"--print"}, MISSING_OPTION);
		test ( new String [] {"--print", "--", "--message"}, MISSING_OPTION);

		String WRONG_VALUE = "Error: value `wrong` for option `--print` is not valid!\n";
		test (new String [] {"--print=wrong"}, WRONG_VALUE);

		String MISSING_VALUE = "Error: missing value for `--message`!\n";
		test (new String [] {"--message"}, MISSING_VALUE);
		test (new String [] {"--message", "--", "hello"}, MISSING_VALUE);
		test (new String [] {"-m", "--", "Hello"}, MISSING_VALUE);
	}

	public void test (String [] args, String expected) {
		String [] real = constructCommand (args);
		String command = "command" + toCommandString (args);
		String out = null;

		try { out = CommandRunner.run (real); }
		catch (Exception e) {
			System.out.println ("got here");
			e.printStackTrace ();
		}

		if ( ! expected.equals (out) ) {
			String msg = String.format (FAILED, command, out, expected);
			System.out.println (msg);
		} else {
			System.out.print (".");
		}
	}

	private static String [] constructCommand (String [] args) {
		int offset = CMD.length;
		String [] result = new String [args.length + offset];
		for ( int i = 0; i < CMD.length; i++ ) { result [i] = CMD[i]; }
		for ( int i = 0; i < args.length; i++ ) { result [i+offset] = args[i]; }
		return result;
	}

	private static String toCommandString (String [] args) {
		StringBuilder builder = new StringBuilder ();
		for ( String arg : args ) {
			builder.append (" ");
			if ( ! arg.contains (" ") ) { builder.append (arg); }
			else if ( ! arg.contains ("=") ) { builder.append ("'").append (arg).append ("'"); }
			else {
				String [] split = arg.split ("=", 2);
				builder.append (split[0]).append ("='").append (split[1]).append ("'");
			}
		}
		return builder.toString ();
	}

}
