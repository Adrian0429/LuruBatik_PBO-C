package Entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.print.ServiceUIFactory;

import main.KeyInputHandler;
import main.panelGame;
import object.obj_chest_brown;
import object.obj_key;


public class Player extends Entity{

	public KeyInputHandler KeyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;

	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	public ArrayList <Entity> inventory = new ArrayList<>();
	public final int inventorySize = 20;
	
	public Player(panelGame gp, KeyInputHandler keyH) {
		
		super(gp);

		this.KeyH = keyH;
		
		screenX = gp.panjangScreen/2 - (gp.tilesize/2);
		screenY = gp.TinggiScreen/2 - (gp.tilesize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefX = solidArea.x;
		solidAreaDefY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		attackArea.width = 36;
		attackArea.height = 36;
				
		setDefaultValue();
		getPlayerImage();
		setItems();
		getPlayerAttackImage();
	}

	
	public void setDefaultValue() {
		worldX = gp.tilesize * 24;
		worldY = gp.tilesize * 44;
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
		
	}
	public void setItems() {
	
	
	}
	public void getPlayerImage() {
				
	up1 = setup("/player/elf_back_walk1");
	up2 = setup("/player/elf_back_walk2");
	up3 = setup("/player/elf_back_walk3");
	down1 = setup("/player/elf_front_walk1");
	down2 = setup("/player/elf_front_walk2");
	down3 = setup("/player/elf_front_walk3");
	right1 = setup("/player/elf_side01_walk1");
	right2 = setup("/player/elf_side01_walk2");
	right3 = setup("/player/elf_side01_walk3");
	left1 = setup("/player/elf_side02_walk1");
	left2 = setup("/player/elf_side02_walk2");
	left3 = setup("/player/elf_side02_walk3");
	titlePict = setup("/PictureStuff/logo_mbatik");
	menuSel = setup("/PictureStuff/scroll");

	}
	public void getPlayerAttackImage() {
		attackDown1 = setup("/player/elf_attackdown_1");
		attackDown2 = setup("/player/elf_attackdown_2");
		attackLeft1 = setup("/player/elf_attackleft_1");
		attackLeft2 = setup("/player/elf_attackleft_2");
		attackRight1 = setup("/player/elf_attackright_1");
		attackRight2 = setup("/player/elf_attackright_2");
	}
	
	
	public void update() {
		if(attacking == true) {
			attacking();
		}
		else if(KeyH.UpFlag == true || KeyH.DownFlag == true || KeyH.LeftFlag == true||
				KeyH.RightFlag == true || KeyH.enterPressed == true) {
		
		
			if(KeyH.UpFlag == true) {
				direction = "up";
				
			}else if(KeyH.LeftFlag == true) {
				direction = "left";
				
			}else if(KeyH.DownFlag == true) {
				direction = "down";
				
			}else if(KeyH.RightFlag == true) {
				direction = "right";
				
			}

			//check tile collision 
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			// check object collision 
			int objIndex = gp.cChecker.checkObject(this, true);
			pickupObject(objIndex);
			
			//npc collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//check event
			gp.eHandler.checkEvent();
			
			gp.KeyH.enterPressed = false;
			
			//if collision false, player can move 
			if(collisionOn == false && KeyH.enterPressed == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					System.out.println("World Y : " + worldY + " World X : " + worldX);
					break;
				case "down":
					worldY += speed;
					System.out.println("World Y : " + worldY + " World X : " + worldX);
					break;
				case "left":
					worldX -= speed;
					System.out.println("World Y : " + worldY + " World X : " + worldX);
					break;
				case "right":
					worldX += speed;
					System.out.println("World Y : " + worldY + " World X : " + worldX);
					break;
				}
			}
			gp.KeyH.enterPressed = false;
			
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
		}
		//invicible time player
		if(invicible == true) {
			invicibleCounter++;
			if(invicibleCounter > 60) {
				invicible = false;
				invicibleCounter = 0;
			}
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.stopMusic();
			gp.playSE(10);
		}
	}
	
	public void setDefaultPosition() {
		worldX = gp.tilesize * 24;
		worldY = gp.tilesize * 44;
		direction = "down";
	}
	
	public void restoreLife() {
		life = maxLife;
		invicible = false;
	}
	public void attacking() {
		charcounter++;
		if(charcounter <= 5) {
			charnumber = 1;
		}
		if(charcounter > 5 && charcounter <= 25) {
			charnumber = 2;
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//adjust player worldX, worldY
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down" : worldY += attackArea.height;break;
			case "left" : worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(charcounter > 25) {
			charnumber = 1;
			charcounter = 0;
			attacking = false;
		}
	}
	public void pickupObject(int i) {
		
		if(i != 999) {
			//pickup only item
			if(gp.obj[i].type == type_pickuponly) {
				gp.obj[i] = null;
			}
			
			//inventory item
			else{
				String text;
				if(inventory.size() != inventorySize) {
					inventory.add(gp.obj[i]);
					gp.playSE(1);
					text = "Got a " + gp.obj[i].name + "!";
					gp.obj[i] = null;
				}
				else {
					text = "Cannot carry anymore";
				}
			}
			
		}
	}
	
	
	public void interactNPC(int i) {
		if(gp.KeyH.enterPressed == true) {
			if(i != 999) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();	
			}
			else {
				gp.playSE(8);
				attacking = true;
			}
		}

	}
	
	public void contactMonster(int i) {
		if (i != 999) {
			if(invicible == false) {
				gp.playSE(9);
				life -= 1;
				invicible = true;
			}
		}
	}
	
	public void damageMonster(int i) {
		if(i != 999) {
			if(gp.monster[i].invicible == false) {
				gp.playSE(9);
				gp.monster[i].life -= 1;
				gp.monster[i].invicible = true;
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
				}
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		//g2.fillOval(x, y, gp.tilesize, gp.tilesize);
		
		BufferedImage image = null;
		switch(direction){
		
		case "up":
			if(attacking ==  false) {
				if(charnumber == 1) {image = up1;}
				if(charnumber == 2) {image = up2;}
				if(charnumber == 3) {image = up3;}
			}
			if(attacking == true) {
				if(charnumber == 1) {image = up1;}
				if(charnumber == 2) {image = up2;}
				if(charnumber == 3) {image = up3;}
			}
			break;
		case "down":
			if(attacking == false){
				if(charnumber == 1) {image = down1;}
				if(charnumber == 2) {image = down2;}
				if(charnumber == 3) {image = down3;}
			}
			if(attacking == true) {
				if(charnumber == 1) {image = attackDown1;}
				if(charnumber == 2) {image = attackDown2;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(charnumber == 1) {image = left1;}
				if(charnumber == 2) {image = left2;}
				if(charnumber == 3) {image = left3;}
			}
			if(attacking == true) {
				if(charnumber == 1) {image = attackLeft1;}
				if(charnumber == 2) {image = attackLeft2;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(charnumber == 1) {image = right1;}
				if(charnumber == 2) {image = right2;}
				if(charnumber == 3) {image = right3;}
			}
			if(attacking == true) {
				if(charnumber == 1) {image = attackRight1;}
				if(charnumber == 2) {image = attackRight2;}
			}
			break;
		}
		if(invicible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
		
		//reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	

}
