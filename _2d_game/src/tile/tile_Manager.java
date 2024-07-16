package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import _2d_game.Game_Panel;
import _2d_game.UtilityTool;

public class tile_Manager {

	Game_Panel GP;
	public Tiles[] tile;
	public int mapTileNum[][];
	
	public tile_Manager(Game_Panel GP) {
	this.GP=GP;
	tile=new Tiles[10];
	mapTileNum=new int[GP.maxWorldCol][GP.maxWorldRow];
	getTileImage();
	loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
			setup(0, "grass", false);
			setup(1, "wall", true);
			setup(2, "water", true);
			setup(3, "earth", false);
			setup(4, "tree", true);
			setup(5, "sand", false);
		
	}
	
	public void setup(int index , String imageName , boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
		tile[index] = new Tiles();
		tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
		tile[index].image = uTool.scaleImage(tile[index].image, GP.TitleSize, GP.TitleSize);
		tile[index].collision = collision;
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String FilePath) {
		try {
			InputStream is=getClass().getResourceAsStream(FilePath);
			BufferedReader br=new BufferedReader(new InputStreamReader (is));
			
			int col=0;
			int row=0;
			
			while (col<GP.maxWorldCol && row<GP.maxWorldRow) {
				String Line= br.readLine();
				while (col<GP.maxWorldCol) {
					String numbers[]=Line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row]=num;
					col++;
				}
				if(col==GP.maxWorldCol) {
					col=0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
		
		}
	}
	
	public void draw(Graphics2D G2) {
		int worldCol=0;
		int worldRow=0;
			
		while (worldCol<GP.maxWorldCol && worldRow<GP.maxWorldRow) {
			
			int tileNum= mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * GP.TitleSize;
			int worldY = worldRow * GP.TitleSize;
			int screenX = worldX - GP.player.worldX + GP.player.screenX;
			int screenY = worldY - GP.player.worldY + GP.player.screenY; 
			
			if (worldX + GP.TitleSize > GP.player.worldX - GP.player.screenX && 
					worldX - GP.TitleSize < GP.player.worldX + GP.player.screenX && 
					worldY + GP.TitleSize > GP.player.worldY - GP.player.screenY &&
					worldY - GP.TitleSize < GP.player.worldY + GP.player.screenY) {
				
				G2.drawImage(tile[tileNum].image, screenX, screenY,null);	
				
			}
			
			
			worldCol++;
			
			if(worldCol==GP.maxWorldCol) {
				worldCol=0;
				worldRow++;
	
			}
		}
	}
}
