package com.mine.minefront;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
 
import com.mine.minefront.display.Display;
import com.mine.minefront.graphics.Screen;
import com.mine.minefront.input.InputManager;
 
  
public class Player implements Runnable{
	private final static int WIDTH = 800,
							 HEIGHT = 600;
	private boolean running;
	private Game game;
	
 
	BufferStrategy bs;
	Graphics g;
	private Display display;
	private Thread thread;
	private Screen screen;
	private BufferedImage img;
	private int[] pixles;
	private int ticks2= 0;
	private InputManager inputManager;
	
	private int newx;
	private int oldx;
	
	
	private int newy;
	private int oldy;
	public Player()
	{
		display = new Display(WIDTH, HEIGHT);
		game = new Game();
		screen = new Screen(game, WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixles = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
        inputManager = new InputManager();
        display.getCanvas().addKeyListener(inputManager);
        display.getFrame().addKeyListener(inputManager);
        display.getCanvas().addFocusListener(inputManager);
        display.getFrame().addFocusListener(inputManager);
        display.getCanvas().addMouseMotionListener(inputManager);
        display.getFrame().addMouseMotionListener(inputManager);
 
	}
	public void render()
	{
		  bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {display.getCanvas().createBufferStrategy(3);return;}
		screen.render();
		for (int i = 0; i < WIDTH*HEIGHT ; i++) {
			pixles[i] = screen.pixels[i];
		}
		g = bs.getDrawGraphics();
		g.setFont(new Font("Arial",Font.PLAIN,30));  
		g.drawImage(img,0,0,WIDTH,HEIGHT,null);
		g.setColor(Color.YELLOW);
		
        g.drawString(ticks2+" Fps", 10,30);
		bs.show();
		g.dispose();
	}
	public void tick()
	{
		game.tick(inputManager.keys);
	}
	@Override
	public void run()
	{
	
		long timer = 0;
		 int fps = 60;
		 int ticks = 0;
	        double timePerTick = 1000000000 / fps;
	        double dalta = 0;
	        long now;
	        long lastTime = System.nanoTime();
	        int counter = 0;
	        while(running)
	        {
	            now = System.nanoTime();
	            dalta += (now - lastTime) / timePerTick;
	            timer += now-lastTime;
	            lastTime = now;
	            if(dalta >=1)
	            {
	                  
	                    render();
	                    tick();
	                     counter++;
	                     ticks++;
	                dalta--;
	              
 	  
	            }
	            if(timer>=1000000000) {
	            	System.err.println(ticks);
	            	ticks2 = ticks; ticks = 0;timer = 0 ;
	            }
	   
	            newx = InputManager.x;
	            if(newx>oldx)
	            	game.controller.trunright = true;
	            else if(newx<oldx)
	            	game.controller.turnleft = true;
	            else if(newx == oldx)
	            {
	            	game.controller.trunright = false;
	            	game.controller.turnleft = false;
	            }
	            oldx = newx;
	            
	            
	            
//	            newy = InputManager.y;
//	            if(newy>oldy)
//	            	game.controller.trunright = true;
//	            if(newy<oldy)
//	            	game.controller.turnleft = true;
//	            if(newy == oldy)
//	            {
//	            	game.controller.trunright = false;
//	            	game.controller.turnleft = false;
//	            }
//	            oldy = newy;
	            
	        }
 
	}
	
 
	public synchronized void start()
	{
		if(running)return;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	public synchronized void stop() throws InterruptedException
	{
		if(!running)return;
		thread.join();
		running = false;
	}
	
	
	
}
