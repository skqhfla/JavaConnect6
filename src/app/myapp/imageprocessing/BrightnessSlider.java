package app.myapp.imageprocessing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.myapp.imageprocessing.DB.ProcedureDB;
import app.myapp.imageprocessing.Listener.LoadImageListener;

public class BrightnessSlider extends JFrame implements ChangeListener, MouseListener{
	JLabel brightValue;
	JSlider slider;
	int brightness;
	BufferedImage ConvertImage;
	Color colour;
	
	public BrightnessSlider() {
		ConvertImage = LoadImageListener.deepCopy(Canvas.resultImage.image);
		
		setTitle("BrightnessSlider");
		setSize(300,130);
		setLayout(new GridLayout(0,1));
		
		if(ProcedureDB.brightness.isEmpty()) {
			brightness = 0;
			slider = new JSlider(JSlider.HORIZONTAL,-100,100,0);
		}
		else {
			brightness = ProcedureDB.brightness.peek();
			slider = new JSlider(JSlider.HORIZONTAL,-100,100,ProcedureDB.brightness.pop());
		}
		
		brightValue = new JLabel("Value : "+Integer.toString(brightness));
		brightValue.setHorizontalAlignment(JLabel.CENTER);
		
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.addChangeListener(this);
		slider.addMouseListener(this);
		add(brightValue);
		add(slider);
		
		setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		brightness = slider.getValue();
		brightValue.setText("Value : "+Integer.toString(brightness));
		for(int y = 0; y < ConvertImage.getHeight(); y++) {
		   for(int x = 0; x < ConvertImage.getWidth(); x++) {
			   
			   colour = new Color(Canvas.originImage.image.getRGB(x, y));
			   
		       int red = colour.getRed() + brightness;
		       int green = colour.getGreen() + brightness;
		       int blue = colour.getBlue() + brightness;
		       
		       if(red>255) red = 255;
		       if(green>255) green = 255;
		       if(blue>255) blue = 255;
		       
		       if(red<0) red = 0;
		       if(green<0) green = 0;
		       if(blue<0) blue = 0;
		       
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
		ProcedureDB.brightness.push(brightness);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
}
