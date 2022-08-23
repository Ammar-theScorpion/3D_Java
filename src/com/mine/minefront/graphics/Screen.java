package com.mine.minefront.graphics;

import java.util.Random;

import com.mine.minefront.Game;

public class Screen extends Render{

	Render test;
	 
	Render3D render;
	Random rand = new Random();
	private Game game;
	public Screen(Game game, int width, int height) {
		super(width, height);
		this.game = game;
		render = new Render3D(   width,height);
		test = new Render(256,256);
		for (int i = 0; i < 256*256; i++) {
			
			test.pixels[i] = rand.nextInt();
		}
		
		 
	}
	public void render()
	{
		for (int i = 0; i < width*height; i++) {
			pixels[i] = 0;
		}
		for (int i = 0; i < 200; i++) {
			
//		int ani =  (int) (Math.sin((System.currentTimeMillis()+i*3)%2000.0 / 2000 * Math.PI * 2) * 200);
//		int ani2 =  (int)  (Math.cos((System.currentTimeMillis()+i*8)%2000.0 / 2000 * Math.PI * 2) * 200); 

	 		}
		render.floor(game );
		render.renderDistanceLimiter();
		draw(render,0,0);
	}

}
