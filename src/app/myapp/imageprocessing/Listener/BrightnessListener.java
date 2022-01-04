package app.myapp.imageprocessing.Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.myapp.imageprocessing.BrightnessSlider;
import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.DB.ProcedureDB;

public class BrightnessListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new BrightnessSlider();
	}
	
}
