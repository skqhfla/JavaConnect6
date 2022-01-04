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

public class SaturationSlider extends JFrame implements ChangeListener, MouseListener{
	
	JLabel saturationValue;
	JSlider slider;
	float saturation = (float) 0.0;
	BufferedImage ConvertImage;
	Color colour;
	
	public SaturationSlider() {
		ConvertImage = LoadImageListener.deepCopy(Canvas.resultImage.image);
		
		setTitle("SaturationSlider");
		setSize(300,130);
		setLayout(new GridLayout(0,1));
		System.out.println("SaturationSlider");
		
		if(ProcedureDB.saturation.isEmpty()) {
			saturation = 0;
			slider = new JSlider(JSlider.HORIZONTAL,-50,50,0);
		}
		else {
			saturation = ProcedureDB.saturation.peek();
			slider = new JSlider(JSlider.HORIZONTAL,-50,50,0);
		}
		
		saturationValue = new JLabel("Value : "+Float.toString(saturation));
		saturationValue.setHorizontalAlignment(JLabel.CENTER);
		
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.addChangeListener(this);
		slider.addMouseListener(this);
		add(saturationValue);
		add(slider);
		
		setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		saturation = (float) (slider.getValue()*0.01);
		saturationValue.setText("Value : "+Float.toString(saturation));
		for(int y = 0; y < ConvertImage.getHeight(); y++) {
		   for(int x = 0; x < ConvertImage.getWidth(); x++) {
			   
			   colour = new Color(Canvas.originImage.image.getRGB(x, y));
			   
		       int red = colour.getRed();
		       int green = colour.getGreen();
		       int blue = colour.getBlue();
		       
		       float[] hsb = Color.RGBtoHSB(red, green, blue, null);
		       
		       float hue = hsb[0];
		       float sat = hsb[1];
		       float brt = hsb[2];
		       
		       sat = sat+saturation;
		       sat = (sat<0) ? 0 : sat;
		       sat = (sat>1) ? 1 : sat;
		       
		       int rgb = Color.HSBtoRGB(hue, sat, brt);
		       
		       red = (rgb>>16)&0xFF;
		       green = (rgb>>8)&0xFF;
		       blue = rgb&0xFF;
			   
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
		ProcedureDB.saturation.push(saturation);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
}
