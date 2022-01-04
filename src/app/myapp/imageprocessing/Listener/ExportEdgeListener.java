package app.myapp.imageprocessing.Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.Convolution;
import app.myapp.imageprocessing.DB.ProcedureDB;

public class ExportEdgeListener implements ActionListener{
	
	BufferedImage ConvertImage;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ConvertImage = LoadImageListener.deepCopy(Canvas.resultImage.image);
		double[][] imageArray = Convolution.imageToArray(ConvertImage);
		double[][] filterBlur = Convolution.getFilter(3);
		
		imageArray = Convolution.convolution(imageArray, filterBlur);
		
		double[][] filterEdge = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };
		imageArray = Convolution.convolution(imageArray, filterEdge);
		
		imageArray = Convolution.arrayInColorBound(imageArray);
		imageArray = Convolution.arrayColorInverse(imageArray);
		
		ConvertImage = Convolution.arrayToImage(imageArray);
		
		Canvas.resultImage.image = LoadImageListener.deepCopy(ConvertImage);
		Canvas.resultImage.repaint();
		ProcedureDB.pushToUndoStack(Canvas.resultImage.image);
	}

}
