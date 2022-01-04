package app.myapp.imageprocessing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.myapp.imageprocessing.Listener.*;

public class Buttons extends JPanel {
	
	private String buttonStrings[] = {"Undo","Redo","Load Image", "Save Image", "흑백 이미지 변환", "edge 추출", "밝기 조정", "채도 조정" ,"대비 조정", "Smoothing"};
	private int buttonLength = buttonStrings.length;
	private JButton buttons[] = new JButton[buttonLength];
 	
	public Buttons() {
		
		setLayout(new GridLayout(0,1,4,4));
		setBackground(Color.DARK_GRAY);
		
		for(int i=0; i<buttonLength; i++) {
			buttons[i] = new JButton(buttonStrings[i]);
			add(buttons[i]);
		}
		
		//Undo
		buttons[0].addActionListener(new UndoListener());
		//Redo
		buttons[1].addActionListener(new RedoListener());
		//Load Image
		buttons[2].addActionListener(new LoadImageListener());
		//Save Image
		buttons[3].addActionListener(new SaveImageListener());
		//흑백 이미지 변환
		buttons[4].addActionListener(new BlackAndWhiteListener());
		//edge 추출
		buttons[5].addActionListener(new ExportEdgeListener());
		//밝기 조정
		buttons[6].addActionListener(new BrightnessListener());
		//채도 조정
		buttons[7].addActionListener(new SaturationListener());
		//대비 조정
		buttons[8].addActionListener(new ContrastListener());
		//Smoothing
		buttons[9].addActionListener(new SmoothingListener());
		
		
	}
}
