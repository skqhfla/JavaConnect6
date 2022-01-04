package app.myapp.imageprocessing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.myapp.imageprocessing.DB.ProcedureDB;
import app.myapp.imageprocessing.Listener.LoadImageListener;

public class ContrastSlider extends JFrame implements ChangeListener, MouseListener{
	JLabel contrastValue;
	JSlider slider;
	int contrast = 0;
	BufferedImage ConvertImage;
	Color colour;
	
	public ContrastSlider() {
		ConvertImage = LoadImageListener.deepCopy(Canvas.resultImage.image);
		
		setTitle("ContrastSlider");
		setSize(300,130);
		setLayout(new GridLayout(0,1));
		System.out.println("ContrastSlider");
		
		if(ProcedureDB.contrast.isEmpty()) {
			contrast = 0;
			slider = new JSlider(JSlider.HORIZONTAL,-100,100,0);
		}
		else {
			contrast = ProcedureDB.contrast.peek();
			slider = new JSlider(JSlider.HORIZONTAL,-100,100,ProcedureDB.contrast.pop());
		}
		
		contrastValue = new JLabel("Value : "+Integer.toString(contrast));
		contrastValue.setHorizontalAlignment(JLabel.CENTER);
		
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.addChangeListener(this);
		slider.addMouseListener(this);
		add(contrastValue);
		add(slider);
		
		setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		contrast = slider.getValue();
		contrastValue.setText("Value : "+Integer.toString(contrast));
		for(int y = 0; y < ConvertImage.getHeight(); y++) {
		   for(int x = 0; x < ConvertImage.getWidth(); x++) {
			   
			   colour = new Color(Canvas.originImage.image.getRGB(x, y));
		       
		       int red = colour.getRed();
		       int green = colour.getGreen();
		       int blue = colour.getBlue();
		       
		       red = (red > 128) ? red+contrast : red-contrast;
		       green = (green > 128) ? green+contrast : green-contrast;
		       blue = (blue > 128) ? blue+contrast : blue-contrast;
		       
		       red = (red > 255) ? 255 : red;
		       green = (green > 255) ? 255 : green;
		       blue = (blue > 255) ? 255 : blue;
		       
		       red = (red < 0) ? 0 : red;
		       green = (green < 0) ? 0 : green;
		       blue = (blue < 0) ? 0 : blue;
		    
		       ConvertImage.setRGB(x, y, new Color(red, green, blue).getRGB());
		   }
		}
		Canvas.resultImage.image = LoadImageListener.deepCopy(ConvertImage);
		Canvas.resultImage.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ProcedureDB.pushToUndoStack(Canvas.resultImage.image);
		ProcedureDB.contrast.push(contrast);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}

}
