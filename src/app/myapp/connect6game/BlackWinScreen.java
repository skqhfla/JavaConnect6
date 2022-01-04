package app.myapp.connect6game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.myapp.connect6game.WhiteWinScreen.BlackLosePanel;
import app.myapp.connect6game.WhiteWinScreen.WhiteWinPanel;

public class BlackWinScreen extends JFrame {
	
	public BlackWinScreen() {
		setSize(1200,900);
		setLayout(new GridLayout(0,2));
		add(new BlackLosePanel());
		add(new WhiteWinPanel());
		
		setVisible(true);
	}
	
	class WhiteWinPanel extends JPanel implements ActionListener{
		
		JLabel announcement;
		JButton restart;
		
		public WhiteWinPanel() {
			
			PlayBoard.unlock = false;
			
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			announcement = new JLabel("WHITE LOSE");
			announcement.setHorizontalAlignment(JLabel.CENTER);
			announcement.setVerticalAlignment(JLabel.CENTER);
			announcement.setForeground(Color.BLACK);
			announcement.setFont(new Font("SANS_SERIF", Font.BOLD, 60));
			
			restart = new JButton("종료");
			restart.addActionListener(this);
			add(announcement, BorderLayout.CENTER);
			add(restart, BorderLayout.SOUTH);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
			
	}
	
	class BlackLosePanel extends JPanel implements ActionListener{
			
		JLabel announcement;
		JButton reconfirm;
		
		public BlackLosePanel(){
			setLayout(new BorderLayout());
			setBackground(Color.BLACK);
			announcement = new JLabel("BLACK WIN");
			announcement.setHorizontalAlignment(JLabel.CENTER);
			announcement.setVerticalAlignment(JLabel.CENTER);
			announcement.setForeground(Color.WHITE);
			announcement.setFont(new Font("SANS_SERIF", Font.BOLD, 60));
			
			reconfirm = new JButton("육목판 재확인");
			reconfirm.addActionListener(this);
			add(announcement, BorderLayout.CENTER);
			add(reconfirm, BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
		}
	}
}
