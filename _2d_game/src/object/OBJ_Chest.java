package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import _2d_game.Game_Panel;

public class OBJ_Chest extends SuperObject{
	Game_Panel gp;
	public OBJ_Chest(Game_Panel gp) {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));
			uTool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
