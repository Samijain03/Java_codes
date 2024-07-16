package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import _2d_game.Game_Panel;

public class OBJ_key extends SuperObject{
    Game_Panel gp;
	public OBJ_key(Game_Panel gp) {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
			uTool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
