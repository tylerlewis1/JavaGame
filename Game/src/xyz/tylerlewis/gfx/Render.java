package xyz.tylerlewis.gfx;

import java.util.Random;

public class Render {
	private int w, h;
	public int[] pixles;
	public int mapSize = 64;
	public int[] tiles = new int[mapSize * mapSize];
	Random r = new Random();
	
	public Render(int w, int h) {
		this.w = w;
		this.h = h;
		pixles = new int[mapSize * mapSize];
		pixles = new int[w * h];
		for(int i = 0; i <  pixles.length; i++) {
			pixles[i] = r.nextInt(0xffffff + 1);
		}
	}
	public void render(int xOff, int yOff) {
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int xx = x + xOff;
				int yy = y + yOff;
				int tileI = ((xx >> 4) & mapSize - 1) + ((yy >> 4) & mapSize - 1) * mapSize;
					pixles[x + y * w] = pixles[x+y];
			}
		}
	}
}
