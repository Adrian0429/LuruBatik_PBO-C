package main;

import java.security.PublicKey;
import java.util.function.IntToDoubleFunction;

public class EventHandler {
    panelGame gp;
    EventRect eventRect[][][];
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(panelGame gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap &&col < gp.maxWorldCol && row < gp.maxWorldRow) {
        	
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 18;
            eventRect[map][col][row].height = 18;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if(col == gp.maxWorldCol) {
            	col = 0;
            	row++;
            	
            	if(row == gp.maxWorldRow) {
            		row = 0; 
            		map++;
            	}
            }
        }

    }

    public void checkEvent(){
    	//check if character is one tile away
    	int xDistance = Math.abs(gp.player.worldX - previousEventX);
    	int yDistance = Math.abs(gp.player.worldY - previousEventY);
    	int distance = Math.max(xDistance, yDistance);
    	if(distance > gp.tilesize) {
    		canTouchEvent = true;
    	}
    	
    	if(canTouchEvent == true) {
    		           
    	        if(hit(0,8,21,"any") == true){cactusHit(8,21,gp.dialogueState);  }
    	               
    	        if(hit(0,5,20,"any") == true){cactusHit(5,20,gp.dialogueState);}
    	               
    	        if(hit(0,0,27,"any") == true)cactusHit(0,27,gp.dialogueState);
    	               
    	        if(hit(0,3,29,"any") == true)cactusHit(3,29,gp.dialogueState);
    	               
    	        if(hit(0,11,29,"any") == true)cactusHit(11,29,gp.dialogueState);
    	               
    	        if(hit(0,10,30,"any") == true)cactusHit(10,30,gp.dialogueState);
    	               
    	        if(hit(0,3,32,"any") == true)cactusHit(3,32,gp.dialogueState);
    	               
    	        if(hit(0,2,35,"any") == true)cactusHit(2,35,gp.dialogueState);
    	               
    	        if(hit(0,0,36,"any") == true)cactusHit(0,36,gp.dialogueState);
    	               
    	        if(hit(0,4,38,"any") == true)cactusHit(4,38,gp.dialogueState);
    	               
    	        if(hit(0,7,42,"any") == true)cactusHit(7,42,gp.dialogueState);
    	               
    	        if(hit(0,1,44,"any") == true)cactusHit(1,44,gp.dialogueState);
                       
    	        if(hit(0,3,44,"any") == true)cactusHit(3,44,gp.dialogueState);
                       
    	        if(hit(0,5,46,"any") == true)cactusHit(5,46,gp.dialogueState);
                       
    	        if(hit(0,38,44,"any") == true)cactusHit(38,44,gp.dialogueState);
    	               
    	        if(hit(0,10,46,"any") == true)cactusHit(10,46,gp.dialogueState);

    	        
    	        
    	        
    	        //heals in spawn
    	        if(hit(0, 23, 45, "down") == true)healingPool(23, 45,gp.dialogueState);
    	        if(hit(0, 22, 45, "down") == true)healingPool(22, 45, gp.dialogueState);
    	        
    	        if(hit(0, 23, 16, "up") == true)teleport(1, 24, 32);
    	        if(hit(1, 24, 32, "down") == true)teleport(0, 23, 16);
    	}
       
    }
   
    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;
        if(map == gp.currentMap) {
        	  gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
              gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
              eventRect[map][col][row].x = col * gp.tilesize + eventRect[map][col][row].x;
              eventRect[map][col][row].y = row * gp.tilesize + eventRect[map][col][row].y;

              if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                  if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                      hit = true; 
                      
                      previousEventX = gp.player.worldX;
                      previousEventY = gp.player.worldY;
                  }
              }

              gp.player.solidArea.x = gp.player.solidAreaDefaultX;
              gp.player.solidArea.y = gp.player.solidAreaDefaultY;
              eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
              eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }
      
        return hit;
    }

    
    public void cactusHit (int col, int row, int gameState) {
    	gp.gameState = gameState;
    	gp.ui.currentDialogue = "Ouch, it's a cactus!";
    	gp.playSE(9);
    	gp.player.life -= 1;
    	canTouchEvent = false;
    }
    
    public void healingPool(int col, int row, int gameState) {
    	if(gp.KeyH.enterPressed == true) {
    		gp.playSE(11);
    		gp.gameState = gameState;
    		gp.ui.currentDialogue = "You drink the water";
    		gp.player.life = gp.player.maxLife;
    	}
    }
    public void teleport(int map, int col, int row) {
    	gp.currentMap = map;
    	gp.player.worldX = gp.tilesize*col;
    	gp.player.worldY = gp.tilesize*row;
    	previousEventX = gp.player.worldX;
    	previousEventY = gp.player.worldY;
    	canTouchEvent = false;
    }
    
    
}
