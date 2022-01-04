package app.myapp.imageprocessing.Listener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.myapp.imageprocessing.Canvas;

public class SaveImageListener implements ActionListener{
	
	JFileChooser fileChooser = new JFileChooser();

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images","jpg","jpeg");
			fileChooser.setFileFilter(filter);
			BufferedImage image = new BufferedImage(Canvas.resultImage.getWidth(), Canvas.resultImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setBackground(Color.WHITE);
			g.clearRect(0, 0, Canvas.resultImage.getWidth(), Canvas.resultImage.getHeight());
			Canvas.resultImage.print(g);
			g.dispose();
	
			int ret = fileChooser.showSaveDialog(null);
	
			if(ret != JFileChooser.APPROVE_OPTION){
				JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			System.out.println(fileChooser.getSelectedFile().toPath());
			ImageIO.write(image, "jpg", new File(fileChooser.getSelectedFile().toPath() + ".jpg"));
		}
		catch (IOException IOe) {
			// TODO: handle exception
			IOe.printStackTrace();
		}
	}

}
