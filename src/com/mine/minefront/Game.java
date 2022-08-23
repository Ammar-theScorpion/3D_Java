package com.mine.minefront;

import java.awt.event.KeyEvent;

import com.mine.minefront.input.Controller;

 

public class Game {
	
	public int time;
	public Controller controller;
	public Game()
	{
		controller = new Controller(); 
	}
	public void tick(boolean keys[])
	{
		time++;
		 boolean forward = keys[KeyEvent.VK_W];
		 boolean left  = keys[KeyEvent.VK_A]; 
		 boolean right  = keys[KeyEvent.VK_D] ;
		 boolean back  = keys[KeyEvent.VK_S]; 
		 
		 controller.tick(forward, back, left, right );
	  
 
	}
}

