package app.myapp.imageprocessing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class ImageProcessor extends JFrame {

	ImageProcessor() {
		setTitle("Java ImageProcessor");
		setSize(1400,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		add(new Buttons(), BorderLayout.EAST);
		add(new Canvas(), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new ImageProcessor();
	}
}
