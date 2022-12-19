package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import main.UtilityTool;
import main.panelGame;

public class Entity {
	
	protected panelGame gp;
	//state
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, up3, left1, left2, left3;
	public BufferedImage right1, right2, right3, down1, down2, down3, titlePict, menuSel;
	public BufferedImage attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public String direction = "down";
	
	public int charcounter = 0;
	public int charnumber = 1;
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle (0,0,0,0);
	public int solidAreaDefX, solidAreaDefY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public boolean invicible = false;
	public int invicibleCounter = 0;
	boolean attacking = false;
	
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int type = 0;
	public int type_pickuponly = 1;
	
	//CHARACTER STATUS
	public boolean alive = true;
	public boolean dying = false;
	boolean hpBarOn = false;
	int hpBarCounter = 0;
	
	public int maxLife;
	public int life;
	
	int dyingCounter = 0;
	//item attribute
	public String description = "";
	
	public Entity(panelGame gp) {
		this.gp = gp;
		
	}
	
	public void setAction() {
		
	}
	public void damageReaction() {
		
	}
	public void checkDrop() {
		
	}
	public void dropItem(Entity droppedItem) {
		for(int i = 0; i < gp.obj[1].length;i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch (gp.player.direction) {
		case "up": 
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "right": 
			direction = "left";
			break;
		case "left":
			direction = "right";
			break;
		}
	}
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invicible == false) {
				gp.player.life -= 1;
				gp.player.invicible = true;
			}
		}
		
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		
		
		charcounter++;
		if(charcounter > 10) {
			if(charnumber == 1) {
				charnumber = 2;
			}
			else if(charnumber == 2) {
				charnumber = 3;
			}
			else if(charnumber == 3) {
				charnumber = 1;
			}
			charcounter = 0;
		}
		
		if(invicible == true) {
			invicibleCounter++;
			if(invicibleCounter > 40) {
				invicible = false;
				invicibleCounter = 0;
			}
		}
	}
	
	
	public void draw(Graphics2D g2) {

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		BufferedImage image = null;
		
		if(worldX + gp.tilesize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tilesize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tilesize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tilesize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction){
			
			case "up":
				if(charnumber == 1) {image = up1;}
				if(charnumber == 2) {image = up2;}
				if(charnumber == 3) {image = up3;}
				break;
			case "down":
				if(charnumber == 1) {image = down1;}
				if(charnumber == 2) {image = down2;}
				if(charnumber == 3) {image = down3;}
				break;
			case "left":
				if(charnumber == 1) {image = left1;}
				if(charnumber == 2) {image = left2;}
				if(charnumber == 3) {image = left3;}
				break;
			case "right":
				if(charnumber == 1) {image = right1;}
				if(charnumber == 2) {image = right2;}
				if(charnumber == 3) {image = right3;}
				break;
			}
			
			//MONSTER HP BAR
			if(type == 2 && hpBarOn == true) {
				double oneScale = (double)gp.tilesize/ maxLife;
				double hpBarValue = oneScale * life;
				
				g2.setColor(Color.BLACK);
				g2.fillRect(screenX-1, screenY-16, gp.tilesize + 2, 12);
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY-15, (int)hpBarValue, 10);
				
				hpBarCounter++;
				
				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			
			if(invicible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
			//death animation
			if(dying == true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
	}
}
		
	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		
		if(dyingCounter <= 5) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 5 && dyingCounter <= 10) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		if(dyingCounter > 10 && dyingCounter <= 15) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 15 && dyingCounter <= 20) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		if(dyingCounter > 20 && dyingCounter <= 25) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 25) {
			dying = false;
			alive = false;
		}
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tilesize, gp.tilesize);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
	}
}