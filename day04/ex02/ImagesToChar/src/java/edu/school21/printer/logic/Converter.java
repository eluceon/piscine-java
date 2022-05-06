package edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;
import java.io.File;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class Converter {
	public static void printImage(String white, String black, String imagePath) {
		
		try {
			File file = new File(imagePath);
			BufferedImage image = ImageIO.read(file);
			int height = image.getHeight();
			int width = image.getWidth();
			
			Ansi.BColor whiteColor = Ansi.BColor.valueOf(white);
        	Ansi.BColor blackColor = Ansi.BColor.valueOf(black);
			ColoredPrinter cp = new ColoredPrinter();
			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width; x++) {
					if (image.getRGB(x, y) == Color.WHITE.getRGB())
					cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, whiteColor);
					else
					cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, blackColor);
				}
				System.out.println();
			}
		} catch (IllegalArgumentException e) {
			System.err.println("\nInvalid color!");
			System.err.println("Choose one of this colors: BLACK BLUE CYAN GREEN MAGENTA NONE RED WHITE YELLOW");
			System.exit(1);
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
}
