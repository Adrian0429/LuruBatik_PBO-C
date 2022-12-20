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
	up2 = setup("/npc/kakek1");
	up3 = setup("/npc/kakek1");
	down1 = setup("/npc/kakek2");
	down2 = setup("/npc/kakek2");
	down3 = setup("/npc/kakek2");
	right1 = setup("/npc/kakek3");
	right2 = setup("/npc/kakek3");
	right3 = setup("/npc/kakek3");
	left1 = setup("/npc/kakek1");
	left2 = setup("/npc/kakek1");
	left3 = setup("/npc/kakek1");

	}
	
	public void setDialogue() {
		dialogues[0] = "Halo nak! Kemana saj\nkamu...";
		dialogues[1] = "Kakek punya keinginan \nmengumpulkan Batik-Batik";
		dialogues[2] = "Benar, batik kain yang \nsering kita pakai";
		dialogues[3] = "Batik-batik ini telah disebar\ndi sekitar kita";
		dialogues[4] = "Lawan monster dan buka\nchest untuk mendapatkan";
		
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
	public void speak2() {
		dialogues[5] = "Wah kamu telah berhasil!";
		dialogues[6] = "Kakek sangat bangga...";
		super.speak2();
	}
}
