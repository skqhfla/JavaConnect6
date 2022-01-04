package app.myapp.imageprocessing.Listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.DB.ProcedureDB;

public class LoadImageListener implements ActionListener{
	
	private JFileChooser fileChooser = new JFileChooser();
	private BufferedImage openImage;
	private Image resizeImage;
	private BufferedImage newImage;
	private String filePath;
	
	private final int imageWidth = 540;
	private final int imageHeight = 720;

	@Override
	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&TXT Files","jpg","jpeg");
		fileChooser.setFileFilter(filter);
		
		int ret = fileChooser.showOpenDialog(null);

		if(ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		filePath = fileChooser.getSelectedFile().getPath();
		FileImageInputStream fileStream;
		try {
			fileStream = new FileImageInputStream(new File(filePath));
			
			Image srcImg = new ImageIcon(filePath).getImage(); // JPEG 포맷인 경우
		    Image imgTarget = srcImg.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
		    int pixels[] = new int[imageWidth * imageHeight]; 
		    PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, imageWidth, imageHeight, pixels, 0, imageWidth); 
		    try {
		        pg.grabPixels();
		    } catch (InterruptedException IEe) {
		        throw new IOException(IEe.getMessage());
		    } 
		    BufferedImage destImg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB); 
		    destImg.setRGB(0, 0, imageWidth, imageHeight, pixels, 0, imageWidth); 
			
			Canvas.originImage.image = deepCopy(destImg);
			Canvas.resultImage.image = deepCopy(destImg);
			Canvas.originImage.repaint();
			Canvas.resultImage.repaint();
			
			ProcedureDB.pushToUndoStack(Canvas.resultImage.image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static BufferedImage deepCopy(BufferedImage bi) {
		
	 ColorModel cm = bi.getColorModel();
	 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	 WritableRaster raster = bi.copyData(null);
	 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	 
	}
	
}
