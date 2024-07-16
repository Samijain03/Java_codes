package _2d_game;

import javax.swing.JFrame;

public class Main {
public static void main(String[] args) {
	
	JFrame window=new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.setTitle("First_2d_game");
	
	Game_Panel game_panel=new Game_Panel();
	window.add(game_panel);
	
	window.pack();
	
	window.setLocationRelativeTo(null);
	window.setVisible(true);
	
	game_panel.setUpGame();
	game_panel.Start_Game_thread();
}
}
