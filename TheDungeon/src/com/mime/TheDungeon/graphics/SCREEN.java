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
		for (int i = 0; i<width*height; i++) {
			pixels[i] = 0;
		}
		for (int i = 0; i<100; i++) {
		
		int anim = (int) (Math.sin((System.currentTimeMillis()+i*2) % 2000.0 / 2000.0 * Math.PI * 2) * 200);
		int anim2 = (int) (Math.cos((System.currentTimeMillis()+i*2) % 2000.0 / 2000.0 * Math.PI * 2) * 200);

		draw(test, (width - 256) / 2 + anim, (height - 256) / 2 + anim2);
		}
	}

}
