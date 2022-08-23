 package com.mine.minefront.graphics;

public class Render {

	protected int width;
	protected int height;
	public int pixels[];
	
	public Render(int width, int height)
	{
		this.width=width;
		this.height =height;
		this.pixels = new int[width*height];

	}
	public void draw(Render render, int xoffset, int yoffset)
	{
		for (int y = 0; y < render.height; y++) {
			int ypos = y + yoffset;
			for (int x = 0; x < render.width; x++) {
				int xpos = x + xoffset;
				if(ypos<0 || ypos>=height)continue;
				if(xpos<0 || xpos>=width)continue;

				pixels[xpos + ypos*width] = render.pixels[x+y*render.width];
						
			}
		}	
	}
}
