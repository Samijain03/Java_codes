package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import _2d_game.Game_Panel;

public class OBJ_Boots extends SuperObject{
	Game_Panel gp;
	public OBJ_Boots(Game_Panel gp) {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));
			uTool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
