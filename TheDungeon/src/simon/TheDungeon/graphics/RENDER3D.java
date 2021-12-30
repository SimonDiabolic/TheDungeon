package simon.TheDungeon.graphics;

import java.util.Random;

import simon.TheDungeon.GAME;
import simon.TheDungeon.input.CONTROLLER;

public class RENDER3D extends RENDER {

	public double[] zBuffer;
	private double renderDistance = 5000;

	public RENDER3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];
	}
	public void floor(GAME game) {

		double ceilingPosition = 8000; // 8000 for no ceiling
		double floorPosition = 15.0;

		double forward = game.controls.z;
		double right = game.controls.x;
		double up = game.controls.y;
		double walking = Math.sin((game.time / 2.0) * 0.4);
		if (CONTROLLER.run) {
			walking = Math.sin((game.time / 3.0) * 1);
		}
		if (CONTROLLER.sneak) {
			walking = 0.0;
		}

		double rotation = game.controls.rotation;
		double cosinus = Math.cos(rotation);
		double sinus = Math.sin(rotation);
		;

		for (int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;

			double z = (floorPosition + up) / ceiling;
			if (CONTROLLER.walking) {
				z = (floorPosition + up + walking) / ceiling;
			}

			if (ceiling < 0) {

				z = (ceilingPosition - up) / -ceiling;
				if (CONTROLLER.walking) {
					z = (ceilingPosition - up - walking) / -ceiling;
				}
			}

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;
				double xx = depth * cosinus + z * sinus; // +right
				double yy = z * cosinus - depth * sinus; // +forward
				int xPix = (int) (xx + right);
				int yPix = (int) (yy + forward);
				zBuffer[x + y * width] = z;
				pixels[x + y * width] = TEXTURE.floor.pixels[(xPix & 7) + (yPix & 7) * 32 /* Times Texture size */];

				if (z > renderDistance) {
					pixels[x + y * width] = 0;
				}
			}

		}
		

	}
	public void walls() {
		Random random = new Random(1);
		for (int i = 0; i < 10000; i++) {
			double x = random.nextDouble();
			double y = random.nextDouble();
			double z = 2;

			int xPixel = (int) (x / z * height / 2 + width / 2);
			int yPixel = (int) (y / z * height / 2 + height / 2);
			if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
				pixels[xPixel + yPixel * width] = 0xfffff;
			}
		}
		for (int i = 0; i < 10000; i++) {
			double x = random.nextDouble()-1;
			double y = random.nextDouble();
			double z = 2;

			int xPixel = (int) (x / z * height / 2 + width / 2);
			int yPixel = (int) (y / z * height / 2 + height / 2);
			if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
				pixels[xPixel + yPixel * width] = 0xfffff;
			}
		}
		for (int i = 0; i < 10000; i++) {
			double x = random.nextDouble();
			double y = random.nextDouble()-1;
			double z = 2;

			int xPixel = (int) (x / z * height / 2 + width / 2);
			int yPixel = (int) (y / z * height / 2 + height / 2);
			if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
				pixels[xPixel + yPixel * width] = 0xfffff;
			}
		}
		for (int i = 0; i < 10000; i++) {
			double x = random.nextDouble()-1;
			double y = random.nextDouble()-1;
			double z = 2;

			int xPixel = (int) (x / z * height / 2 + width / 2);
			int yPixel = (int) (y / z * height / 2 + height / 2);
			if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
				pixels[xPixel + yPixel * width] = 0xfffff;
			}
		}
	}
	public void renderDistanceLimiter() {
		for (int i = 0; i < width * height; i++) {
			int colour = pixels[i];
			int brightness = (int) (renderDistance / (zBuffer[i]));

			if (brightness < 0) {
				brightness = 0;
			}
			if (brightness > 255) {
				brightness = 255;
			}

			int r = (colour >> 16) & 0xff;
			int g = (colour >> 8) & 0xff;
			int b = (colour) & 0xff;

			r = r * brightness / 255;
			g = g * brightness / 255;
			b = b * brightness / 255;

			pixels[i] = r << 16 | g << 8 | b;
		}
	}
}
