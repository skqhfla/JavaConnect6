package app.myapp.connect6game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WelcomeScreen extends JFrame implements ActionListener{
	JLabel announcement;
	JTextField textfield;
	JButton button;
	int neutralDolNum;
	
	WelcomeScreen() {
		setTitle("Welcome!");
		setSize(300,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0,1));
		
		announcement = new JLabel("중립돌 갯수를 입력해주세요! (0개 이상 5개 이하)");
		announcement.setHorizontalAlignment(JLabel.CENTER);
		
		textfield = new JTextField();
		
		button = new JButton("게임 시작!");
		
		add(announcement);
		add(textfield);
		add(button);
		
		button.addActionListener(this);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		neutralDolNum = Integer.parseInt(textfield.getText());
		
		if(neutralDolNum > 5) {
			announcement.setText("중립돌이 너무 많아요 ㅠㅠ");
			announcement.setForeground(Color.RED);
		}
		else if(neutralDolNum < 0) {
			announcement.setText("0개 이상의 중립돌을 지정해주세요 ㅠㅠ");
			announcement.setForeground(Color.RED);
		}
		else {
			new Connect6Game(neutralDolNum);
			dispose();
		}
		
	}
	
}
