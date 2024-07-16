package _2d_game;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_key;

public class AssetSetter {

	public Game_Panel gp;
	
	public AssetSetter(Game_Panel gp) {
    this.gp=gp;
	}

	public void setObject() {
		gp.SObj[0] = new OBJ_key(gp);
		gp.SObj[0].worldX = 18 * gp.TitleSize;
		gp.SObj[0].worldY = 39 * gp.TitleSize;
		
		gp.SObj[1] = new OBJ_key(gp);
		gp.SObj[1].worldX = 23 * gp.TitleSize;
		gp.SObj[1].worldY = 7 * gp.TitleSize;
		
		gp.SObj[2] = new OBJ_key(gp);
		gp.SObj[2].worldX = 37 * gp.TitleSize;
		gp.SObj[2].worldY = 7 * gp.TitleSize;
		
		gp.SObj[3] = new OBJ_Door(gp);
		gp.SObj[3].worldX = 10 * gp.TitleSize;
		gp.SObj[3].worldY = 11 * gp.TitleSize;
		
		gp.SObj[4] = new OBJ_Door(gp);
		gp.SObj[4].worldX = 8 * gp.TitleSize;
		gp.SObj[4].worldY = 28 * gp.TitleSize;
		
		gp.SObj[5] = new OBJ_Door(gp);
		gp.SObj[5].worldX = 12 * gp.TitleSize;
		gp.SObj[5].worldY = 22 * gp.TitleSize;
		
		gp.SObj[6] = new OBJ_Chest(gp);
		gp.SObj[6].worldX = 10 * gp.TitleSize;
		gp.SObj[6].worldY = 7 * gp.TitleSize;
		
		gp.SObj[7] = new OBJ_Boots(gp);
		gp.SObj[7].worldX = 37 * gp.TitleSize;
		gp.SObj[7].worldY = 42 * gp.TitleSize;
	}
}
