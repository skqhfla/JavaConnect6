package app.myapp.connect6game;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Connect6Game extends JFrame {
	
	int neutralDolNum;
	
	static public PlayBoard playBoard;
	public BlackPanel blackPanel;
	public WhitePanel whitePanel;
	
	Connect6Game(int neutralDolNum) {
		setTitle("Java Connect6Game");
		setSize(1200,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		this.neutralDolNum = neutralDolNum;
		playBoard = new PlayBoard(neutralDolNum);
		blackPanel = new BlackPanel();
		whitePanel = new WhitePanel();
		
		add(blackPanel);
		add(playBoard);
		add(whitePanel);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new WelcomeScreen();
	}
}
