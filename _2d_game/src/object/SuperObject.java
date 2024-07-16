package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import _2d_game.Game_Panel;
import _2d_game.UtilityTool;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX ,worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D G2 , Game_Panel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY; 
		
		if (worldX + gp.TitleSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.TitleSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.TitleSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.TitleSize < gp.player.worldY + gp.player.screenY) {
			
			G2.drawImage(image, screenX, screenY, gp.TitleSize,gp.TitleSize,null);	
			
		}
	}
}
