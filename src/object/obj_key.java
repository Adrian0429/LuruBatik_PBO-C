package object;


import Entity.Entity;
import main.panelGame;

public class obj_key extends Entity{
	public obj_key(panelGame gp) {
		super(gp);
		name = "KUNCI";
		down1 = setup("/objects/01");
		description = "[" + name + "]\nDigunakan untuk membuka \nchest dan pintu.";
	}	
}
