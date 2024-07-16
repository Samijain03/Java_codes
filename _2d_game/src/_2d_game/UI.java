package _2d_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_key;

public class UI {

	Game_Panel gp;
	Font ariel_40B , ariel_80B;
	BufferedImage KeyImage;
	public boolean messageOn = false;
	public String message = " ";
	int messageCounter = 0;
	public boolean gameFinished = false ;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(Game_Panel gp) {
		this.gp = gp;
		
		ariel_40B = new Font("Arial" , Font.BOLD , 40);
		ariel_80B = new Font("Arial" , Font.BOLD , 80);
		OBJ_key key = new OBJ_key(gp);
		KeyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D G2)
	{
		if(gameFinished == true) {
			
			G2.setFont(ariel_40B);
			G2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "Your Time is : " + dFormat.format(playTime) + " ! ";
			textLength = (int)G2.getFontMetrics().getStringBounds(text, G2).getWidth();			
			x = gp.width/2 - textLength/2;
			y = gp.height/2 + (gp.TitleSize * 4);
			
			G2.drawString(text, x, y);
			
			G2.setFont(ariel_80B);
			G2.setColor(Color.blue);
			
			text = "Congratulations! ";
			textLength = (int)G2.getFontMetrics().getStringBounds(text, G2).getWidth();			
			x = gp.width/2 - textLength/2;
			y = gp.height/2 + (gp.TitleSize * 2);
			G2.drawString(text, x, y);
			
			gp.gameThread = null;
		}
		else {
			G2.setFont(ariel_40B);
			G2.setColor(Color.white);
			G2.drawImage(KeyImage, gp.TitleSize/2, gp.TitleSize/2, gp.TitleSize, gp.TitleSize, null);
			G2.drawString("x " + gp.player.hasKey, 74, 65);
			
			//Time
			playTime += (double)1/60;
			G2.drawString("Time: " + dFormat.format(playTime), gp.TitleSize*11, 65);
			
			//Message
			if(messageOn == true) {
				G2.setFont(G2.getFont().deriveFont(30));
				G2.drawString(message, gp.TitleSize/2, gp.TitleSize * 4);
				
				messageCounter++;
				if(messageCounter >180) {
					messageCounter = 0;
					messageOn = false ;	
		}
		
		}
		}
	}
}
