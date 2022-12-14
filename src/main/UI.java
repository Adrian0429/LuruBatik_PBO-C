package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import Entity.Entity;
import object.obj_heart;


public class UI {
	panelGame gp;
	Graphics2D g2;
	Font goblinFont;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameEnd = false;
	public String currentDialogue = "";
	int command = 0;
	int subState = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	
	public UI(panelGame gp) {
		this.gp = gp;
		
		InputStream is = getClass().getResourceAsStream("/font/AGoblinAppears.ttf");
		try {
			goblinFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//CREATE HUD OBJECT
		Entity heart = new obj_heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(goblinFont);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		
		//title state
		if(gp.gameState == gp.titleState) {
			gp.stopMusic();
			drawTitleScreen();
		}
		//play state
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		//pause state
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		//dialogue state
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		if(gp.gameState == gp.dialogueState2) {
			drawPlayerLife();
			drawDialogueScreen2();
		}
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		
		//character state
		if(gp.gameState == gp.characterState){
			drawPlayerLife();
			drawInventory();
		}
		// game over state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// end game state
		if(gp.gameState == gp.endingScreenState) {
			drawEndingScreen();
		}
	}
	public void drawEndingScreen() {
		gp.stopMusic();
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.panjangScreen, gp.TinggiScreen);
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,65F));
		command = 0;
		text = "GAME FINISHED";
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tilesize*4;
		g2.drawString(text, x, y);
		
		// main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//retry
		g2.setFont(g2.getFont().deriveFont(30f));
		text = "Quit";
		x = getXforCenteredText(text);
		y = gp.tilesize * 7;
		g2.drawString(text, x, y);
		if(command == 0) {
			g2.drawString(">", x-40, y);
		}	
		
		//nama
		g2.setFont(g2.getFont().deriveFont(20f));
		text = "- Luru Batik -";
		x = getXforCenteredText(text);
		y += gp.tilesize *3;
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(15f));
		text = "by Calvin & Adrian";
		x = getXforCenteredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
	}
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.panjangScreen, gp.TinggiScreen);
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
		
		text = "GAME OVER";
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tilesize*5;
		g2.drawString(text, x, y);
		
		// main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y = gp.tilesize * 7;
		g2.drawString(text, x, y);
		if(command == 0) {
			g2.drawString(">", x-40, y);
		}
		
		//title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 65;
		g2.drawString(text, x, y);
		if(command == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	public void drawPlayerLife() {
		int x = gp.tilesize / 2;
		int y = gp.tilesize / 2;
		int i = 0;
		
		//DRAW blank heart
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tilesize;
		}
		//RESET
		x = gp.tilesize/2;
		y = gp.tilesize /2;
		i = 0; 
		
		//DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tilesize;
		}
	}
	
	
	public void drawTitleScreen() {
		//background color
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.panjangScreen, gp.TinggiScreen);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 65F));
		String text = "Luru Batik";
		int x = getXforCenteredText(text);
		int y = gp.tilesize*3;
		
		//shadow on text
		g2.setColor(Color.gray);
		g2.drawString(text, x+4, y+4);
		//main color text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//title character image 
		x = gp.panjangScreen/2 - (gp.tilesize*2)/2;
		y += gp.tilesize*2;
		g2.drawImage(gp.player.titlePict, x, y, gp.tilesize*2, gp.tilesize*2, null);
		
		//menu options
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tilesize*4;
		g2.drawString(text, x, y);
		if(command == 0) {
			//you can use draw image for this
			g2.drawImage(gp.player.menuSel, x-(gp.tilesize + 4), y-gp.tilesize, null);
			//g2.drawString(">", x - gp.tilesize, y);
		}
		
		text = "CONTINUE";
		x = getXforCenteredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if(command == 1) {
			//you can use draw image for this
			g2.drawImage(gp.player.menuSel, x-(gp.tilesize + 4), y-gp.tilesize, null);
			//g2.drawString(">", x - gp.tilesize, y);
		}
		
		text = "EXIT";
		x = getXforCenteredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if(command == 2) {
			//you can use draw image for this
			g2.drawImage(gp.player.menuSel, x-(gp.tilesize + 4), y-gp.tilesize, null);
			//g2.drawString(">", x - gp.tilesize, y);
		}
		
	}
	
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 72F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.TinggiScreen/2;
		
		g2.drawString(text, x, 	y);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.panjangScreen/2 - length/2;
		return x;
	}
	
	public void drawDialogueScreen() {
		//window
		int x = gp.tilesize * 2;
		int y = gp.tilesize /2;
		int width = gp.panjangScreen -(gp.tilesize*4);
		int height = gp.tilesize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += gp.tilesize;
		y += gp.tilesize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+= 40;
		}
		
	}
	
	public void drawDialogueScreen2() {
		//window
		int x = gp.tilesize * 2;
		int y = gp.tilesize /2;
		int width = gp.panjangScreen -(gp.tilesize*4);
		int height = gp.tilesize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += gp.tilesize;
		y += gp.tilesize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+= 40;
		}
		
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0, 190);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public void drawInventory() {
		//frame
		int frameX = gp.tilesize * 13;
		int frameY = gp.tilesize;
		int frameWidth = gp.tilesize * 6;
		int frameHeight = gp.tilesize * 5;
		int x = frameX + gp.tilesize;
		int y = frameY + gp.tilesize*4;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		
		//draw player items
		for(int i=0; i<gp.player.inventory.size();i++) {
			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
			slotX += gp.tilesize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += gp.tilesize;
			}
		}
		//cursor
		int cursorX = slotXstart + (gp.tilesize * slotCol);
		int cursorY = slotYstart + (gp.tilesize * slotRow);
		int cursorWidth = gp.tilesize;
		int cursorHeight = gp.tilesize;
		
		//draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		//description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tilesize*3;
		
		
		//write desc text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tilesize-5;
		g2.setFont(g2.getFont().deriveFont(10F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if(itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			for(String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
			
		}
	}
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(16F));
		
		//POSITION
		int frameX = gp.tilesize * 6;
		int frameY = gp.tilesize;
		int frameWidth = gp.tilesize * 8;
		int frameHeigt = gp.tilesize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeigt);
		
		switch(subState) {
		case 0: options_top(frameX, frameY);break;
		case 1: break;
		case 2: option_control(frameX, frameY);break;
		case 3: break;
		}
		gp.KeyH.enterPressed = false;
	}
	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		
		//TITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tilesize;
		g2.drawString(text, textX, textY);

		//full screen option
		textX = frameX + gp.tilesize;
		textY += gp.tilesize * 2;
		g2.drawString("Full Screen", textX, textY);
		if(command == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.KeyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
					gp.setFullScreen();
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
			}
		}
		
		//music
		textY += gp.tilesize;
		g2.drawString("Music", textX, textY);
		if(command == 1) {
			g2.drawString(">", textX-25, textY);
		}
		
		//control
		textY += gp.tilesize;
		g2.drawString("Control", textX, textY);
		if(command == 2) {
			g2.drawString(">", textX-25, textY);
			if(gp.KeyH.enterPressed == true) {
				subState = 2;
				command = 0;
			}
		}
		
		//end game
		textY += gp.tilesize;
		g2.drawString("End Game", textX, textY);
		if(command == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.KeyH.enterPressed == true) {
				gp.stopMusic();
				gp.playSE(10);
				gp.gameState = gp.titleState;
				command = 0;
			}
		}
		
		//back
		textY += gp.tilesize*2;
		g2.drawString("Back", textX, textY);
		if(command == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.KeyH.enterPressed == true) {
				gp.gameState = gp.playState;
			}
		}
		
		//check box for full screen
		textX = frameX + gp.tilesize*6-50;
		textY = frameY + gp.tilesize*2 + 26;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY,24,24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY,24,24);
		}
		
		//music slider
		textY += gp.tilesize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
	}
	public void option_control(int frameX, int frameY) {
		int textX;
		int textY;
		
		//title
		String text = "Control";
		textX = getXforCenteredText(text)-96;
		textY = frameY + gp.tilesize * 2;
		g2.drawString("Move Up", textX, textY); textY += gp.tilesize;
		g2.drawString("Move Left", textX, textY); textY += gp.tilesize;
		g2.drawString("Move Down", textX, textY); textY += gp.tilesize;
		g2.drawString("Move Right", textX, textY); textY += gp.tilesize;
		g2.drawString("Interact", textX, textY); textY += gp.tilesize;
		g2.drawString("Attack", textX, textY); textY += gp.tilesize;
		g2.drawString("Inventory", textX, textY); textY += gp.tilesize;
		g2.drawString("Options", textX, textY); textY += gp.tilesize;
		
		textX = frameX + gp.tilesize*6-12;
		textY = frameY + gp.tilesize*2;
		g2.drawString("W", textX, textY); textY += gp.tilesize;
		g2.drawString("A", textX, textY); textY += gp.tilesize;
		g2.drawString("S", textX, textY); textY += gp.tilesize;
		g2.drawString("D", textX, textY); textY += gp.tilesize;
		g2.drawString("Enter", textX, textY); textY += gp.tilesize;
		g2.drawString("Enter", textX, textY); textY += gp.tilesize;
		g2.drawString("I", textX, textY); textY += gp.tilesize;
		g2.drawString("Esc", textX, textY); textY += gp.tilesize;
		//back

		if(command == 0) {
			if(gp.KeyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
}

