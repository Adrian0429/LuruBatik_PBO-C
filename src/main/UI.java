package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.PublicKey;

import javax.security.auth.x500.X500Principal;
import javax.xml.transform.Templates;

import object.obj_key;

public class UI {
	panelGame gp;
	Graphics2D g2;
	Font arial = new Font("Arial", Font.PLAIN, 40);
	Font arial_80B = new Font("Arial", Font.BOLD, 80);
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameEnd = false;
	public String currentDialogue = "";
	int command = 0;

	public UI(panelGame gp) {
		this.gp = gp;

	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial);
		g2.setColor(Color.white);
		
		
		//title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//playstate
		if(gp.gameState == gp.playState) {
			//do something
		}
		//pausestate
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//dialogue state
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}
	
	public void drawTitleScreen() {
		//background color
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.panjangScreen, gp.TinggiScreen);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
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
		g2.drawImage(gp.player.logo, x, y, gp.tilesize*2, gp.tilesize*2, null);
		
		//menu optionss
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tilesize*4;
		g2.drawString(text, x, y);
		if(command == 0) {
			//you can use draw image for this
			g2.drawString(">", x - gp.tilesize, y);
		}
		
		text = "CONTINUE";
		x = getXforCenteredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if(command == 1) {
			//you can use draw image for this
			g2.drawString(">", x - gp.tilesize, y);
		}
		
		text = "EXIT";
		x = getXforCenteredText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if(command == 2) {
			//you can use draw image for this
			g2.drawString(">", x - gp.tilesize, y);
		}
		
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
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
		int height = gp.tilesize * 5;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
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
}

