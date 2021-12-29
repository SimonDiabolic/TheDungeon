package com.mime.TheDungeon.graphics;

import java.util.Random;

public class SCREEN extends RENDER {

	private RENDER test;

	public SCREEN(int width, int height) {
		super(width, height);
		Random random = new Random();
		test = new RENDER(256, 256);
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt();
		}
	}

	public void render() {
		draw(test, 0, 0);
	}

}
