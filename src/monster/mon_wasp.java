package monster;

import java.util.Random;

import Entity.Entity;
import main.panelGame;
import object.obj_scroll;

public class mon_wasp extends Entity {

	public mon_wasp(panelGame gp) {
		super(gp);
		type = 2;
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
		up1 = setup("/monster/wasp_idle1");
		up2 = setup("/monster/wasp_idle2");
		up3 = setup("/monster/wasp_idle2");
		down1 = setup("/monster/wasp_walk1");
		down2 = setup("/monster/wasp_walk2");
		down3 = setup("/monster/wasp_walk2");
		left1 = setup("/monster/wasp_walk1");
		left2 = setup("/monster/wasp_walk2");
		left3 = setup("/monster/wasp_walk2");
		right1 = setup("/monster/wasp_walk1");
		right2 = setup("/monster/wasp_walk2");
		right3 = setup("/monster/wasp_walk2");
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
	public void checkDrop() {
		int i = new Random().nextInt(100) + 1;
		
		if(i < 50) {
			dropItem(new obj_scroll(gp));
		}
		
	}
}