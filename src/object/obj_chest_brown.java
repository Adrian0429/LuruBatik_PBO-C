package object;

import Entity.Entity;
import main.panelGame;

public class obj_chest_brown extends Entity{

	public obj_chest_brown(panelGame gp) {
		super(gp);
		name = "Brown Chest";
		down1 = setup("/objects/chests_1");	 
		collision = true;
		description = "[" + name + "]\nDigunakan untuk mendapat\nhadiah";
	}	
}
