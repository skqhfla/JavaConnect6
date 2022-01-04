package app.myapp.connect6game;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackPanel extends JPanel implements ActionListener{
	
	JLabel name;
	JButton undo, surrender, exit;
	
	public BlackPanel() {
		setBounds(0,0,250,900);
		setBackground(Color.BLACK);
		
		name = new JLabel("BLACK");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SANS_SERIF", Font.BOLD, 60));
		
		undo = new JButton("무르기");
		surrender = new JButton("기권");
		undo.addActionListener(this);
		surrender.addActionListener(this);
		
		add(name);
		add(undo);
		add(surrender);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Connect6Game.playBoard.unlock) {
			JButton button = (JButton) e.getSource();
			
			if(button == surrender) {
				new WhiteWinScreen();
			}
			if(button == undo) {
				
				if(BadukalDB.points.size() > 3 && Connect6Game.playBoard.badukalCount/2%2 != 0) {
					BadukalDB.points.pop();
					BadukalDB.colors.pop();
					Connect6Game.playBoard.badukalCount--;
					if(BadukalDB.points.size() > 3) {
						BadukalDB.points.pop();
						BadukalDB.colors.pop();
						Connect6Game.playBoard.badukalCount--;
					}
					System.out.println(Connect6Game.playBoard.badukalCount);
				}
				Connect6Game.playBoard.repaint();
			}
		}
	}
}
