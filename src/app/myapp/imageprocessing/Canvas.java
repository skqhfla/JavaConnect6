package app.myapp.imageprocessing;

import java.awt.Color;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	
	static public OriginImage originImage = new OriginImage();
	static public ResultImage resultImage = new ResultImage();
	
	
	Canvas() {
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		
		originImage.setBounds(50,33,540,720);
		resultImage.setBounds(645,33,540,720);
		
		add(originImage);
		add(resultImage);
	}

}
