package com.mime.TheDungeon.graphics;

import com.mime.TheDungeon.GAME;

public class RENDER3D extends RENDER {

	public RENDER3D(int width, int height) {
		super(width, height);
	}

	public void floor(GAME game) {

		double ceilingPosition = 8.0;
		double floorPosition = 8.0;

		double forward = game.controls.z;
		double right = game.controls.x;

		double rotation = game.controls.rotation;
		double cosinus = Math.cos(rotation);
		double sinus = Math.sin(rotation);

		for (int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;

			double z = floorPosition / ceiling;
			if (ceiling < 0) {

				z = ceilingPosition / -ceiling;
			}

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;
				double xx = depth * cosinus + z * sinus; //+right
				double yy = z * cosinus - depth * sinus; //+forward
				int xPix = (int) (xx+right);
				int yPix = (int) (yy+forward);
				pixels[x + y * width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8;
//if(z > renderDistance)return
			}

		}
	}

}
