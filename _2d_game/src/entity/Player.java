package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import _2d_game.Game_Panel;
import _2d_game.KeyHandler;
import _2d_game.UtilityTool;

public class Player extends Entity{

	Game_Panel gp;
	KeyHandler Keyh;
	
	public final int screenX;
	public final int screenY;
	
	public int hasKey = 0;
	int standCounter;
	
	
	public Player(Game_Panel gp,KeyHandler Keyh) {
		this.gp=gp;
		this.Keyh=Keyh;
		
		screenX=gp.width/2 - (gp.TitleSize/2);
		screenY=gp.height/2 - (gp.TitleSize/2);
		
		solidArea = new Rectangle(8,16,32,32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		worldX= gp.TitleSize*23;
		worldY=gp.TitleSize*21;
		speed=4;
		direction="down";
	}
	
	public void getPlayerImage() {
		
		up1 = setup("boy_up_1");
		up2 = setup("boy_up_2");
		down1 = setup("boy_down_1");
		down2 = setup("boy_down_2");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
		
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool utool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = utool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void update() {
		if (Keyh.uppressed==true||Keyh.downpressed==true||Keyh.rightpressed==true||Keyh.leftpressed==true) {
			if (Keyh.uppressed==true) {
				direction="up";
			}
			
			else if (Keyh.downpressed==true) {
				direction="down";
			}
			else if (Keyh.leftpressed) {
				direction="left";
			}
			else if (Keyh.rightpressed==true) {
				direction="right";
			}
			
			//check tile collision
			collisionOn = false;
			gp.cchecker.checkTile(this);
			
			//Check Object Collision
			int objectIndex = gp.cchecker.checkObject(this, true);
			pickUpObject(objectIndex);
			
			//if collision is false player can move
			if (collisionOn == false) {
				
				switch (direction) {
				case "up":	worldY-=speed;
					break;

				case "down": worldY+=speed;	
					break;
					
				case "left": worldX-=speed;	
					break;
					
				case "right": worldX+=speed;	
					break;
				}
			}
			
			spriteCounter++;
			
			if (spriteCounter>10) {
				if (spriteNum==1) {
					spriteNum=2;
				}
				else if (spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
		else {
				standCounter++;
				if(standCounter == 20) {
					spriteNum = 1;
				    standCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {
			String ObjectName = gp.SObj[i].name;
			
			switch(ObjectName) {
			case "Key" :
			gp.playSE(1);
			hasKey++;
			gp.SObj[i] = null;
			gp.ui.showMessage("You Recieved a Key! ");
			break;
			
			case "Door" :
			if(hasKey > 0) {
			gp.playSE(3);
			gp.SObj[i] = null;
			hasKey--;
			gp.ui.showMessage("You Opened the door! ");
			}
			else {
				gp.ui.showMessage("You need a key ");
			}
			break;
			
			case "Boots":
			gp.playSE(2);
			speed += 2;
			gp.SObj[i] = null;
			gp.ui.showMessage("You Recieved speed boost ");
			break;
			
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
			break;
		}
	}
	}
	
	public void draw(Graphics2D G2) {
		BufferedImage image=null;
		switch (direction) {
		case "up":
			if (spriteNum==1) {
				image=up1;
			}
			if (spriteNum==2)
			{
				image=up2;
			}
			break;
		case "down":
			if (spriteNum==1) {
				image=down1;
			}
			if (spriteNum==2)
			{
				image=down2;
			}
			break;
		case "right":
			if (spriteNum==1) {
				image=right1;
			}
			if (spriteNum==2)
			{
				image=right2;
			}
			break;
		case "left":
			if (spriteNum==1) {
				image=left1;
			}
			if (spriteNum==2)
			{
				image=left2;
			}
			break;	
		
		}
		G2.drawImage(image, screenX, screenY,null);
	}
}
