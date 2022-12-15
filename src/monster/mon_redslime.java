package monster;

import java.util.Random;
import java.util.jar.Attributes.Name;

import Entity.Entity;
import main.panelGame;

public class mon_redslime extends Entity {

	public mon_redslime(panelGame gp) {
		super(gp);
		
		name = "Red Slime";
		speed = 1;
		maxLife = 4;
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
		up1 = setup("/monster/redslime_down_1");
		up2 = setup("/monster/redslime_down_2");
		down1 = setup("/monster/redslime_down_1");
		down2 = setup("/monster/redslime_down_2");
		left1 = setup("/monster/redslime_down_1");
		left2 = setup("/monster/redslime_down_2");
		right1 = setup("/monster/redslime_down_1");
		right2 = setup("/monster/redslime_down_2");
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 20) {	
			Random rand = new Random();
			int i = rand.nextInt(20)+1;
			
			if(i <= 10) {
				direction = "up";
			}if(i> 10 && i <= 20) {
				direction = "down";
			}
			actionLockCounter = 0;
		}
	}
	
}
