package object;


import Entity.Entity;
import main.panelGame;

public class obj_chestGold extends Entity{

	public obj_chestGold(panelGame gp) {
		super(gp);
		type = 4;
		name = "Gold Chest";
		down1 = setup("/objects/chest_3");
		collision = true;
		description = "[" + name + "]\nDigunakan untuk mendapat\nhadiah emas";
	}

}
