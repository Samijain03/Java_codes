package _2d_game;

import entity.Entity;

public class CollisonChecker {

	public Game_Panel gp;
	
	public CollisonChecker(Game_Panel gp) {
		this.gp=gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.TitleSize;
		int entityRightCol = entityRightWorldX/gp.TitleSize;
		int entityTopRow = entityTopWorldY/gp.TitleSize;
		int entityBottomRow = entityBottomWorldY/gp.TitleSize;
		
		int tileNum1,tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.TitleSize;
			tileNum1 = gp.TileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.TileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}		
			
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TitleSize;
			tileNum1 = gp.TileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.TileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}		
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TitleSize;
			tileNum1 = gp.TileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.TileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}		
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.TitleSize;
			tileNum1 = gp.TileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.TileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}		
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0; i < gp.SObj.length; i++) {
			if (gp.SObj[i] != null) {
				
				//Get entity Solid Area Position
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//Get Object Solid Area Position
				gp.SObj[i].solidArea.x = gp.SObj[i].worldX + gp.SObj[i].solidArea.x;
				gp.SObj[i].solidArea.y = gp.SObj[i].worldY + gp.SObj[i].solidArea.y;
				
				switch (entity.direction) {
				case "up":entity.solidArea.y -= entity.speed;
				if (entity.solidArea.intersects(gp.SObj[i].solidArea)) {
					if(gp.SObj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				break;

				case "down":entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.SObj[i].solidArea)) {
                	if(gp.SObj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				break;
				
				case "left":entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.SObj[i].solidArea)) {
                	if(gp.SObj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				break;
					
				case "right":entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.SObj[i].solidArea)) {
                	if(gp.SObj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				break;	
				
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.SObj[i].solidArea.x = gp.SObj[i].solidAreaDefaultX;
				gp.SObj[i].solidArea.y = gp.SObj[i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
}
