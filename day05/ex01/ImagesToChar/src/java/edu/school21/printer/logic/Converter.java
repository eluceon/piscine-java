package edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;
import java.io.File;

public class Converter {
	public static void printImage(char white, char black, File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		int height = image.getHeight();
		int width = image.getWidth();

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; x++) {
				if (image.getRGB(x, y) == Color.WHITE.getRGB())
					System.out.print(white);
				else
					System.out.print(black);
			}
            System.out.println();
		}

	}
	
}
