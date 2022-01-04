package app.myapp.imageprocessing.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.myapp.imageprocessing.ContrastSlider;

public class ContrastListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new ContrastSlider();
	}

}
