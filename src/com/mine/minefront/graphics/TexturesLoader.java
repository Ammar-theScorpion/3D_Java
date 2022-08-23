package com.mine.minefront.graphics;

 
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TexturesLoader {
	public static Render texture = textureLoader("/texture/floor.png");
	public static Render texture2 = textureLoader("/texture/mom.png");
	public static Render textureLoader(String string)
	{
		try {
			BufferedImage image = ImageIO.read(TexturesLoader.class.getResource(string));
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height); 
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		} catch (IOException e) {
		 
			throw new RuntimeException();
		}
	}

}
