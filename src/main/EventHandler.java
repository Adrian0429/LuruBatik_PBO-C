package main;

import java.awt.Rectangle;

public class EventHandler {
    panelGame gp;
    EventRect eventRect[][];
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(panelGame gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
      
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
        	
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 18;
            eventRect[col][row].height = 18;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if(col == gp.maxWorldCol) {
            	col = 0;
            	row++;
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
    		 if(hit(1,20, "left") == true) {cactusHit(1,20,gp.dialogueState);}
    		 
    	        if(hit(8,21,"left") == true){cactusHit(8,21,gp.dialogueState);  }
    	   
    	        if(hit(5,20,"left") == true){cactusHit(5,20,gp.dialogueState);}
    	   
    	        if(hit(0,27,"left") == true)cactusHit(0,27,gp.dialogueState);
    	  
    	        if(hit(3,29,"left") == true)cactusHit(3,29,gp.dialogueState);
    	     
    	        if(hit(11,29,"left") == true)cactusHit(11,29,gp.dialogueState);
    	    
    	        if(hit(10,30,"left") == true)cactusHit(10,30,gp.dialogueState);
    	      
    	        if(hit(3,32,"left") == true)cactusHit(3,32,gp.dialogueState);
    	   
    	        if(hit(2,35,"left") == true)cactusHit(2,35,gp.dialogueState);
    	   
    	        if(hit(0,36,"left") == true)cactusHit(0,36,gp.dialogueState);
    	     
    	        if(hit(4,38,"left") == true)cactusHit(4,38,gp.dialogueState);
    	   
    	        if(hit(7,42,"left") == true)cactusHit(7,42,gp.dialogueState);
    	    
    	        if(hit(1,44,"left") == true)cactusHit(1,44,gp.dialogueState);

    	        if(hit(3,44,"left") == true)cactusHit(3,44,gp.dialogueState);

    	        if(hit(5,46,"left") == true)cactusHit(5,46,gp.dialogueState);

    	        if(hit(38,44,"left") == true)cactusHit(38,44,gp.dialogueState);
    	    
    	        if(hit(10,46,"left") == true)cactusHit(10,46,gp.dialogueState);

    	        
    	        
    	        
    	        //heals in spawn
    	        if(hit(23, 45, "down") == true)healingPool(23, 45,gp.dialogueState);
    	        if(hit(22, 45, "down") == true)healingPool(22, 45, gp.dialogueState);
    	}
       
    }
   
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tilesize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tilesize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("right")|| reqDirection.contentEquals("up") || reqDirection.contentEquals("down")){
                hit = true; 
                
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    
    public void damagePit (int col, int row, int gameState) {
    	gp.gameState = gameState;
    	gp.ui.currentDialogue = "You fall into a pit!";
    	gp.player.life -= 1;
    	eventRect[col][row].eventDone = true;
    	canTouchEvent = false;
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
}
