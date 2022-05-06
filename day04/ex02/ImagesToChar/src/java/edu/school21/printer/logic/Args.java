package edu.school21.printer.logic;

import java.io.IOException;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {
	private static final String IMAGE = "target/resources/it.bmp";

	@Parameter(names = { "--white", "-w" }, description = "white pixels")
	private String white;

	@Parameter(names = { "--black", "-b" }, description = "black pixels")
	private String black;

	public void run() {
		Converter.printImage(white, black, IMAGE);
	}

}