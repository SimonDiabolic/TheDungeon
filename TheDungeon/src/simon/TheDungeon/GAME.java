package simon.TheDungeon;

import java.awt.event.KeyEvent;

import simon.TheDungeon.input.CONTROLLER;

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
		boolean jump = key[KeyEvent.VK_SPACE];
		boolean crouch = key[KeyEvent.VK_C];
		boolean sprint = key[KeyEvent.VK_SHIFT];
		// if (forward) System.out.println("W");

		controls.tick(forward, back, right, left, jump, crouch, sprint);

	}

}
