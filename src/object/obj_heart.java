package object;

import Entity.Entity;
import main.panelGame;

public class obj_heart extends Entity {

	public obj_heart(panelGame gp) {
		super(gp);
		name = "Heart";
		image = setup("/objects/heart_full");
		image2 = setup("/objects/heart_half");
		image3 = setup("/objects/heart_blank");
	}	
}
