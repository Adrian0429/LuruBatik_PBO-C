package object;


import Entity.Entity;
import main.panelGame;

public class obj_scroll extends Entity{

	public obj_scroll(panelGame gp) {
		super(gp);
		type = 6;
		name = "Batik";
		down1 = setup("/objects/Scroll");
		collision = true;
		description = "[" + name + "]\nBarang yang diinginkan \noleh Kakek.";
	}
}
