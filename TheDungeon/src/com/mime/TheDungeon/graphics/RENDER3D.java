package com.mime.TheDungeon.graphics;

public class RENDER3D extends RENDER {

	public RENDER3D(int width, int height) {
		super(width, height);
	}

	public void floor() {
		for (int y = 0; y < height; y++) {
			double yDepth = y-height/2;
			double z = 100.0/yDepth;
			
			for (int x = 0; x < width; x++) {
				double xDepth = x-width/2;
				xDepth *= z;
				int xx = (int) (xDepth) & 5;
				pixels[x+y*width] = xx *128 ;


			}

		}
	}

}
