package edu.school21.printer.app;

import java.io.File;

import edu.school21.printer.logic.Converter;

public class Program {
	private static final String USAGE = "Invalid arguments\nRead README.txt";
	private static final String IMAGE = "target/resources/it.bmp";
	
	public static void main(String[] args) {
		if (args.length != 2 || args[0].length() != 1 || args[1].length() != 1) {
			System.err.println(USAGE);
			System.exit(1);
		}
		try {
            Converter.printImage(args[0].charAt(0), args[1].charAt(0), new File(IMAGE));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
