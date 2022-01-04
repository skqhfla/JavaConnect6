package app.myapp.imageprocessing.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.Convolution;
import app.myapp.imageprocessing.DB.ProcedureDB;

public class SmoothingListener implements ActionListener{

	BufferedImage ConvertImage;

	@Override
	public void actionPerformed(ActionEvent e) {
		ConvertImage = LoadImageListener.deepCopy(Canvas.resultImage.image);	
		Kernel kernel = new Kernel(3, 3, new float[] { 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f });
	    BufferedImageOp op = new ConvolveOp(kernel);
	    ConvertImage = op.filter(ConvertImage, null);
		
		Canvas.resultImage.image = LoadImageListener.deepCopy(ConvertImage);
		Canvas.resultImage.repaint();
		ProcedureDB.pushToUndoStack(Canvas.resultImage.image);
	}
	
	public double[][] GaussianBlur(double[][] filter){
		double[][] output = new double[filter.length][filter[0].length];
		for(int i=0; i<filter.length; i++) {
			for(int j=0; j<filter[i].length; j++) {
				filter[i][j] /= 16;
			}
		}
		return output;
	}
}
