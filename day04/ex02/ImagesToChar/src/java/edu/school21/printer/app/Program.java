package edu.school21.printer.app;

import java.io.File;
import com.beust.jcommander.JCommander;

import edu.school21.printer.logic.Args;

public class Program {
	private static final String USAGE = "Invalid number of arguments\nRead README.txt";
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println(USAGE);
			System.exit(1);
		}
		try {
			Args arguments = new Args();
            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(args);
            arguments.run();
		} catch (Exception e) {
            System.err.println(e.getMessage());
			System.exit(1);
        }
	}
}
