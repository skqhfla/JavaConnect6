package app.myapp.imageprocessing.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.myapp.imageprocessing.SaturationSlider;

public class SaturationListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new SaturationSlider();
	}
}
