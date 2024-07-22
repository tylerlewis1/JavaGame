package xyz.tylerlewis.gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {

	
	
	public String path;
	public int width, height;
	public int[] pixles;
	
	public SpriteSheet(String path) {
		this.path = path;
		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(SpriteSheet.class.getResource(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.width = sheet.getWidth();
		this.height = sheet.getHeight();
		pixles = sheet.getRGB(0, 0, width, height, null, 0, width);
		for(int i = 0; i < pixles.length; i++) {
			if(pixles[i] != -1) {
				System.out.println(pixles[i]);
			}
		}	
	}	
}