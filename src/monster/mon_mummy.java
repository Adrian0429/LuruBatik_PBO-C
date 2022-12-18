package monster;

import java.util.Random;

import Entity.Entity;
import main.panelGame;

public class mon_mummy extends Entity {

	public mon_mummy(panelGame gp) {
		super(gp);
		type = 2;
		name = "Mummy";
		speed = 1;
		maxLife = 6;
		life = maxLife;
		direction = "down";

		//SOLID AREA FOR MONSTER
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefX = solidArea.x;
		solidAreaDefY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/monster/mummy_up1");
		up2 = setup("/monster/mummy_up2");
		down1 = setup("/monster/mummy_down1");
		down2 = setup("/monster/mummy_down2");
		left1 = setup("/monster/mummy_left1");
		left2 = setup("/monster/mummy_left2");
		right1 = setup("/monster/mummy_right1");
		right2 = setup("/monster/mummy_right2");
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 20) {	
			Random rand = new Random();
			int i = rand.nextInt(50)+1;
			
			if(i <= 10) {
				direction = "up";
			}if(i> 10 && i <= 20) {
				direction = "down";
			}if(i> 30 && i <= 40) {
				direction = "left";
			}if(i> 40 && i <= 50) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
	}
}