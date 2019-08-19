package PJ;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class BGM extends Thread {
	private Player player;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private File file;
	private boolean isLoop;
	
	public BGM(String name,boolean isLoop) {
		try {
			
			this.isLoop=isLoop;
			file=new File(Main.class.getResource("../BGM/"+name).toURI());
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			player=new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	@Override
	public void run(){
	try {
	
		do {
			player.play();
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			player=new Player(bis);
		}while(isLoop);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}
	public void close() {
		isLoop=false;
		player.close();
		this.interrupt();
	}
}
