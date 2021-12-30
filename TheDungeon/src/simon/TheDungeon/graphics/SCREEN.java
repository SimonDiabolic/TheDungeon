package simon.TheDungeon.graphics;

import java.util.Random;

import simon.TheDungeon.GAME;

public class SCREEN extends RENDER {

	private RENDER test;
	private RENDER3D render;

	public SCREEN(int width, int height) {
		super(width, height);
		Random random = new Random();
		render = new RENDER3D(width, height);
		test = new RENDER(256, 256);
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt();
		}
	}

	public void render(GAME game) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}
		for (int i = 0; i < 50; i++) {

		}
		render.floor(game);
		render.renderDistanceLimiter();
		//render.walls();
		draw(render, 0, 0);
	}

}
