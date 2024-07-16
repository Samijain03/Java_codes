package _2d_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.tile_Manager;

public class Game_Panel extends JPanel implements Runnable{
    
	//Screen Setting
	final int OrignalTitleSize = 16;//16 x 16 tiles
	final int Scale=3;
	
	public final int TitleSize=OrignalTitleSize*Scale;//48 x 48 tiles
	public final int maxsizecol=16 ;
	public final int maxsizerow=12 ;
	
	public final int width=TitleSize * maxsizecol;//768 pixels
	public final int height=TitleSize* maxsizerow;//576 pixels
	
	//World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	//FPS
	int FPS=60;
	
	tile_Manager TileM = new tile_Manager(this);
	Thread gameThread;
	KeyHandler Keyh = new KeyHandler();
	Sound music = new Sound();
	Sound se = new Sound();
	public UI ui = new UI(this);
	
	//Entity and Object
	public Player player=new Player(this,Keyh);
	public CollisonChecker cchecker = new CollisonChecker(this);
	public SuperObject SObj[] = new SuperObject[10];
	public AssetSetter aSetter = new AssetSetter(this);
	
	public Game_Panel() {
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(Keyh);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		aSetter.setObject();
		
		playMusic(0);
	}
	
	public void Start_Game_thread() {
		
		gameThread =new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval=1000000000/FPS;
		double Delta=0;
		long LastTime=System.nanoTime();
		long currentTime;
		
		while (gameThread !=null) {
			
			currentTime=System.nanoTime();
			Delta += (currentTime-LastTime)/drawInterval;
			LastTime=currentTime;
			if (Delta>=1) {
				//1. Update: update information such as character position
				update();
				//2. Draw: draw the screen with the updated information
				repaint();
				
				Delta--;
			}
		}
	}
	
	public void update() {
		player.update();
	}

	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		
		Graphics2D G2= (Graphics2D)G;
		//Tile
		TileM.draw(G2);
		
		//Object
		for (int i = 0; i < 9; i++) {
			if (SObj[i] != null) {
				SObj[i].draw(G2, this);
			}
		}
		
		//Player
		player.draw(G2);
		
		//UI
		ui.draw(G2);
		
		G2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}
