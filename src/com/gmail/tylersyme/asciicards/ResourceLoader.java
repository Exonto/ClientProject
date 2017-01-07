package com.gmail.tylersyme.asciicards;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	public static BufferedImage getLocalImage(String path) {
		try {
			InputStream in = ResourceLoader.class.getResourceAsStream(path);
			BufferedImage img = ImageIO.read(in);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static File getLocalFile(String path)
	{
		try {
			InputStream in = ResourceLoader.class.getResourceAsStream(path);
			BufferedImage img = ImageIO.read(in);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/

}
