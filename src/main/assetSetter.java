package main;

import Entity.NPC_Bakul;
import Entity.NPC_Kakek;
import monster.mon_mummy;
import monster.mon_redslime;
import monster.mon_wasp;
import object.obj_chestGold;
import object.obj_chest_brown;
import object.obj_key;

public class assetSetter {

	panelGame gp;
	
	public assetSetter(panelGame gp) {
		this.gp = gp;
	}
	
	
	public void setObject() {
		int mapNum = 0;
		int i=0;
		gp.obj[mapNum][i] = new obj_key(gp);
		gp.obj[mapNum][i].worldX = 18*gp.tilesize;
		gp.obj[mapNum][i].worldY = 37*gp.tilesize;
		i++;
		gp.obj[mapNum][i] = new obj_key(gp);
		gp.obj[mapNum][i].worldX = 22*gp.tilesize;
		gp.obj[mapNum][i].worldY = 12*gp.tilesize;
		i++;
		gp.obj[mapNum][i] = new obj_chest_brown(gp);
		gp.obj[mapNum][i].worldX = 28*gp.tilesize;
		gp.obj[mapNum][i].worldY = 36*gp.tilesize;
		i++;
		gp.obj[mapNum][i] = new obj_chestGold(gp);
		gp.obj[mapNum][i].worldX = 42*gp.tilesize;
		gp.obj[mapNum][i].worldY = 43*gp.tilesize;
		i++;
		gp.obj[mapNum][i] = new obj_chestGold(gp);
		gp.obj[mapNum][i].worldX = 27*gp.tilesize;
		gp.obj[mapNum][i].worldY = 12*gp.tilesize;
		i++;
	}
	
	public void setNPC() {
		int mapNum = 0;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_Bakul(gp);
		gp.npc[mapNum][i].worldX = 25*gp.tilesize;
		gp.npc[mapNum][i].worldY = 39*gp.tilesize;
		
		i++;
		mapNum = 1;
		gp.npc[mapNum][i] = new NPC_Kakek(gp);
		gp.npc[mapNum][i].worldX = 25*gp.tilesize;
		gp.npc[mapNum][i].worldY = 21*gp.tilesize;
		i++;
	}
	
	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*19;
		gp.monster[mapNum][i].worldY = gp.tilesize*34;
		i++;        
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*46;
		gp.monster[mapNum][i].worldY = gp.tilesize*14;
		i++;       
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*41;
		gp.monster[mapNum][i].worldY = gp.tilesize*5;
		i++;   
		gp.monster[mapNum][i] = new mon_mummy(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*2;
		gp.monster[mapNum][i].worldY = gp.tilesize*23;
		i++;    
		gp.monster[mapNum][i] = new mon_mummy(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*1;
		gp.monster[mapNum][i].worldY = gp.tilesize*30;
		i++;    
		gp.monster[mapNum][i] = new mon_mummy(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*4;
		gp.monster[mapNum][i].worldY = gp.tilesize*42;
		i++;      
		gp.monster[mapNum][i] = new mon_wasp(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*2;
		gp.monster[mapNum][i].worldY = gp.tilesize*13;
		i++;      
		gp.monster[mapNum][i] = new mon_wasp(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*15;
		gp.monster[mapNum][i].worldY = gp.tilesize*3;
		i++;    
		gp.monster[mapNum][i] = new mon_wasp(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*13;
		gp.monster[mapNum][i].worldY = gp.tilesize*14;
		i++;  
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*12;
		gp.monster[mapNum][i].worldY = gp.tilesize*15;
		i++;     
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*2;
		gp.monster[mapNum][i].worldY = gp.tilesize*2;
		i++;
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*43;
		gp.monster[mapNum][i].worldY = gp.tilesize*4;
		i++;
		mapNum = 1;
		gp.monster[mapNum][i] = new mon_redslime(gp);
		gp.monster[mapNum][i].worldX = gp.tilesize*5;
		gp.monster[mapNum][i].worldY = gp.tilesize*5;
		i++;
	}
}
