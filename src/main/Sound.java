package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/bgm.wav");
		soundURL[1] = getClass().getResource("/sound/key.wav");
		soundURL[2] = getClass().getResource("/sound/chest.wav");
		soundURL[3] = getClass().getResource("/sound/option.wav");
		soundURL[4] = getClass().getResource("/sound/bgm2.wav");
		soundURL[5] = getClass().getResource("/sound/menu.wav");
		soundURL[6] = getClass().getResource("/sound/inventory.wav");
		soundURL[7] = getClass().getResource("/sound/select.wav");
		soundURL[8] = getClass().getResource("/sound/punch.wav");
		soundURL[9] = getClass().getResource("/sound/hit.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
