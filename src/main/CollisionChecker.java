package main;

import Entity.Entity;

public class CollisionChecker {

	panelGame gp;
	public CollisionChecker(panelGame gp) {
		this.gp = gp;
	}
	
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tilesize;
		int entityRightCol = entityRightWorldX/gp.tilesize;
		int entityTopRow = entityTopWorldY/gp.tilesize;
		int entityBottomRow = entityBottomWorldY/gp.tilesize;
		
		int tilenum1,tilenum2;
		
	
		switch (entity.direction) {
		
		case "up" :
			entityTopRow = (entityTopWorldY - entity.speed) /gp.tilesize;
			tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			
			entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.tilesize;
			tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tilesize;
			tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tilesize;
			tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			
			if(gp.obj[gp.currentMap][i] != null) {
				//get entity solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// get object solid area position 
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
				
				switch (entity.direction) {
				case "up" :
					entity.solidArea.y -= entity.speed;
					break;
					
				case "down":
					entity.solidArea.y += entity.speed;
					break;
					
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					
					break;
				}
				
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if(gp.obj[gp.currentMap][i].collision == true) {
						entity.collisionOn = true;
					}
				if(player == true) {
					index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefX;
				entity.solidArea.y = entity.solidAreaDefY;
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefY;
			}
			
		}
		return index;
	}
	
	//npc
	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;
		
		for(int i = 0; i < target[1].length; i++) {
			
			if(target[gp.currentMap][i] != null) {
				//get entity solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// get object solid area position 
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
				
				switch (entity.direction) {
				case "up" :
					entity.solidArea.y -= entity.speed;
					break;
					
				case "down":
					entity.solidArea.y += entity.speed;
					break;
					
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
					
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					if(target[gp.currentMap][i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefX;
				entity.solidArea.y = entity.solidAreaDefY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefY;
			}
			
		}
		return index;
	}


	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer = false;
		//get entity solid area position 
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		// get object solid area position 
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch (entity.direction) {
		case "up" :
			entity.solidArea.y -= entity.speed;
			break;
			
		case "down":
			entity.solidArea.y += entity.speed;
			break;
			
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		entity.solidArea.x = entity.solidAreaDefX;
		entity.solidArea.y = entity.solidAreaDefY;
		gp.player.solidArea.x = gp.player.solidAreaDefX;
		gp.player.solidArea.y = gp.player.solidAreaDefY;
		
		return contactPlayer;
	}
}
