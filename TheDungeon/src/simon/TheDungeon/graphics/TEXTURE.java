package simon.TheDungeon.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class TEXTURE {
	public static RENDER floor = loadBitmap("res/textures/floor.png");

	public static RENDER loadBitmap(String fileName){
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(fileName));
		int width = image.getWidth();
		int height = image.getHeight();
		RENDER result = new RENDER(width,height);
		image.getRGB(0, 0,width,height,result.pixels,0,width);
		return result;
		} catch (Exception e) {
			System.out.println("Ressource not found");
			throw new RuntimeException(e);
		}
	}
}
