package com.mine.minefront.graphics;

import com.mine.minefront.Game;

public class Render3D extends Render {
 
	double zBuffer[];
	public Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width*height];
		// TODO Auto-generated constructor stub
	}
	double timer = 0;
	
	public void floor(Game game)
	{
		double floorP = 8;
		double cellingP = 8;
		double forward = game.controller.z;
		double right = game.controller.x;
		double rotation = game.controller.rotation;
		
		double cos = Math.cos(rotation);
		double sin = Math.sin(rotation);
	 
		for (int y = 0; y < height; y++) {
			double celling =  (y - height/2.0)/height ;
					
			
			double z = floorP/celling;
			if (celling <0) 
				z = cellingP/-celling;
			timer=0;
				for (int x = 0; x < width; x++) {
					double depth = (x -  width/(2.0))/ height  ;
					depth*=(z);
					
					  double xx = (depth*cos  + z*sin   ) ;//||
					  double yy = (z*cos - depth*sin  )  ; 
					  int xPix = (int)(xx+right);
					  int yPix = (int)(yy+ forward);
					  zBuffer[x+y*width] = z;
					  if( z>150)
					  {
						  
						 
						  pixels[x+y*width] = TexturesLoader.texture2.pixels[(xPix&639)+(yPix&639)*640];
					  }
					  else pixels[x+y*width] = TexturesLoader.texture.pixels[(xPix&7)+(yPix&7)*8];
//					pixels[x+y*width] = (  ( xPix &15   )*16 ) | ((yPix&15)*16)    ; 
			}
					
			
		}
	}

	public void renderDistanceLimiter()
	{
		for (int i = 0; i < width*height; i++) {
			int colour = pixels[i];
			int brigtness = (int)(3000/zBuffer[i]);
			
			if(brigtness<0)brigtness = 0;
			if(brigtness>255)brigtness = 255;
			
			int r = (colour>>16)& 0xff;
			int g = (colour>>8)& 0xff;
			int b = (colour)& 0xff;
			
			r = r*brigtness/255;
			g = g*brigtness/255;
			b = b*brigtness/255;
			pixels[i] = r<<16 | g<<8 | b;//rgb place r on it's right poisition00 00 00 00 00 000 0 0 0 0
		}	
	}
}
		
