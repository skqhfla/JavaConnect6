package app.myapp.imageprocessing.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import java.awt.Color;

import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.DB.ProcedureDB;

public class BlackAndWhiteListener implements ActionListener {
	
	BufferedImage ConvertImage;

	@Override
	public void actionPerformed(ActionEvent e) {
		ConvertImage = LoadImageListener.deepCopy(Canvas.resultImage.image);
		
		for(int y = 0; y < ConvertImage.getHeight(); y++) {
		   for(int x = 0; x < ConvertImage.getWidth(); x++) {
		       Color colour = new Color(ConvertImage.getRGB(x, y));
		       int Y = (int) (0.299 * colour.getRed() + 0.587 * colour.getGreen() + 0.114 * colour.getBlue());
		       ConvertImage.setRGB(x, y, new Color(Y, Y, Y).getRGB());
		   }
		}
		Canvas.resultImage.image = LoadImageListener.deepCopy(ConvertImage);
		Canvas.resultImage.repaint();
		ProcedureDB.pushToUndoStack(Canvas.resultImage.image);
		ProcedureDB.blackAndWhite = Canvas.resultImage.image;
		
	}

}
