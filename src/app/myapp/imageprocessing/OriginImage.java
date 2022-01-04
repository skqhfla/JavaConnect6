package app.myapp.imageprocessing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class OriginImage extends JPanel{
	
	public BufferedImage image;

	
	OriginImage() {
		setBackground(Color.GRAY);
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
            repaint();
        }
    }
	

}
