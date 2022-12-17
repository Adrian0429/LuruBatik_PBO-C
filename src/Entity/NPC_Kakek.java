package Entity;

import main.panelGame;

public class NPC_Kakek extends Entity {

	public NPC_Kakek(panelGame gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		
		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
	up1 = setup("/npc/kakek1");
	down1 = setup("/npc/kakek2");
	right1 = setup("/npc/kakek3");
	left1 = setup("/npc/kakek1");

	}
	
	public void setDialogue() {
		dialogues[0] = "Hello, kid";
		dialogues[1] = "tugasmu adalah untuk \nmengumpulkan Batik-Batik";
		dialogues[2] = "benar, Batik kain yang \nsering kita pakai";
		dialogues[3] = "Batik-batik ini telah disebar \ndi sekitar kita";
		dialogues[4] = "Gunakan kunci untuk \nmembuka chest";
		
	}
	
	
	public void setAction() {
		
		//actionLockCounter++;
		//
		//if(actionLockCounter == 20) {
		//	
		//	Random rand = new Random();
		//	int i = rand.nextInt(20)+1;
		//	
		//	if(i <= 10) {
		//		direction = "up";
		//	}if(i> 10 && i <= 20) {
		//		direction = "down";
		//	}
		//	actionLockCounter = 0;
		//}
		//
	}
	
	public void speak() {
		
		super.speak();
	}

}
