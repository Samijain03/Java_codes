package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import _2d_game.Game_Panel;

public class OBJ_Door extends SuperObject{
	Game_Panel gp;
	public OBJ_Door(Game_Panel gp) {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
			uTool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
}
}
