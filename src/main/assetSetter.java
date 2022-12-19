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
		gp.obj[0] = new obj_key(gp);
		gp.obj[0].worldX = 29*gp.tilesize;
		gp.obj[0].worldY = 48*gp.tilesize;
		
		gp.obj[1] = new obj_key(gp);
		gp.obj[1].worldX = 21*gp.tilesize;
		gp.obj[1].worldY = 13*gp.tilesize;
		
		gp.obj[2] = new obj_chest_brown(gp);
		gp.obj[2].worldX = 28*gp.tilesize;
		gp.obj[2].worldY = 36*gp.tilesize;
	
		gp.obj[3] = new obj_chestGold(gp);
		gp.obj[3].worldX = 42*gp.tilesize;
		gp.obj[3].worldY = 43*gp.tilesize;
		
		gp.obj[4] = new obj_chestGold(gp);
		gp.obj[4].worldX = 27*gp.tilesize;
		gp.obj[4].worldY = 12*gp.tilesize;
		
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Bakul(gp);
		gp.npc[0].worldX = 25*gp.tilesize;
		gp.npc[0].worldY = 39*gp.tilesize;
		
//		gp.npc[1] = new NPC_Kakek(gp);
//		gp.npc[1].worldX = 30*gp.tilesize;
//		gp.npc[1].worldY = 39*gp.tilesize;
	}
	
	public void setMonster() {
		gp.monster[0] = new mon_redslime(gp);
		gp.monster[0].worldX = gp.tilesize*19;
		gp.monster[0].worldY = gp.tilesize*34;
		
		gp.monster[1] = new mon_redslime(gp);
		gp.monster[1].worldX = gp.tilesize*46;
		gp.monster[1].worldY = gp.tilesize*14;
		
		gp.monster[2] = new mon_redslime(gp);
		gp.monster[2].worldX = gp.tilesize*41;
		gp.monster[2].worldY = gp.tilesize*5;
		
		gp.monster[4] = new mon_mummy(gp);
		gp.monster[4].worldX = gp.tilesize*2;
		gp.monster[4].worldY = gp.tilesize*23;
		
		gp.monster[5] = new mon_mummy(gp);
		gp.monster[5].worldX = gp.tilesize*1;
		gp.monster[5].worldY = gp.tilesize*30;
		
		gp.monster[6] = new mon_mummy(gp);
		gp.monster[6].worldX = gp.tilesize*4;
		gp.monster[6].worldY = gp.tilesize*42;
		
		gp.monster[7] = new mon_wasp(gp);
		gp.monster[7].worldX = gp.tilesize*2;
		gp.monster[7].worldY = gp.tilesize*13;
		
		gp.monster[8] = new mon_wasp(gp);
		gp.monster[8].worldX = gp.tilesize*15;
		gp.monster[8].worldY = gp.tilesize*3;
		
		gp.monster[9] = new mon_wasp(gp);
		gp.monster[9].worldX = gp.tilesize*13;
		gp.monster[9].worldY = gp.tilesize*14;
		
		gp.monster[10] = new mon_redslime(gp);
		gp.monster[10].worldX = gp.tilesize*12;
		gp.monster[10].worldY = gp.tilesize*15;
		
		gp.monster[11] = new mon_redslime(gp);
		gp.monster[11].worldX = gp.tilesize*2;
		gp.monster[11].worldY = gp.tilesize*2;
		
		gp.monster[12] = new mon_redslime(gp);
		gp.monster[12].worldX = gp.tilesize*43;
		gp.monster[12].worldY = gp.tilesize*4;
		
		
	}
}
