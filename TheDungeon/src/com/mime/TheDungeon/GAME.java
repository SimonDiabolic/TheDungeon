package com.mime.TheDungeon;

import java.awt.event.KeyEvent;

import com.mime.TheDungeon.input.CONTROLLER;

public class GAME {
	public int time;
	public CONTROLLER controls;
	public GAME() {
		controls = new CONTROLLER();
		
	}
	public void tick(boolean[] key) {
		time++;
		boolean forward = key[KeyEvent.VK_W];
		boolean back = key[KeyEvent.VK_S];
		boolean right = key[KeyEvent.VK_D];
		boolean left = key[KeyEvent.VK_A];
		boolean turnLeft = key[KeyEvent.VK_LEFT];
		boolean turnRight = key[KeyEvent.VK_RIGHT];
		//if (forward) System.out.println("W");
		
		controls.tick(forward, back, right, left, turnLeft, turnRight);
		
	}

}
