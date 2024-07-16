package _2d_game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage orignal , int width , int height) {
		
		BufferedImage scaledImage = new BufferedImage(width , height , orignal.getType());
	    Graphics2D G2 = scaledImage.createGraphics();
	    G2.drawImage(orignal, 0, 0, width, height, null);
	    G2.dispose();
	    
	    return scaledImage;
	}
}
