package app.myapp.connect6game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JPanel;

public class PlayBoard extends JPanel implements MouseMotionListener, MouseListener{
	
	Point point = new Point(0,0);
	Point ellipsePoint = new Point(0,0);
	
	Ellipse2D.Double ellipse;
	Ellipse2D.Double badukal;
	
	String[][] board = new String[19][19];
	
	Color thisTurnColor;
	
	static boolean unlock = true;
	
	static int neutralDolNum;
	int initNum = 0;
	int[] neutralDol;
	int ellipseX = 0;
	int ellipseY = 0;
	static int badukalCount = 1;
	

	public PlayBoard(int neutralDolNum) {
		setBounds(250,25,700,700);
		setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		this.neutralDolNum = neutralDolNum;
		neutralDol = new int[neutralDolNum*2];
		
		for(int y=0; y<19; y++) {
			for(int x=0; x<19; x++) {
				board[y][x] = "X";
			}
		}
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //바둑판 그리기 
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < 19; i++) {
			g.drawLine(32+i*36, 32, 32+i*36, 680);
			g.drawLine(32, 32+i*36, 680, 32+i*36);
		}
		for(int i = 140; i < 573; i+=216) {
			g.fillOval(i-3, 140-3, 6, 6);
			g.fillOval(i-3, 356-3, 6, 6);
			g.fillOval(i-3, 572-3, 6, 6);
		}
		
		//놓아진 바둑돌
		for(int i=0; i<BadukalDB.points.size(); i++) {
			badukal = new Ellipse2D.Double(BadukalDB.points.get(i).x, BadukalDB.points.get(i).y, 30, 30);
			g2.setPaint(BadukalDB.colors.get(i));
			g2.fill(badukal);
			g2.draw(badukal);
		}
		
		//마우스 따라 다니는 바둑돌 
		if(point.x>=14 && point.x<=698 && point.y>=14 && point.y<=698) {
			g2.setPaint(thisTurnColor);
		    g2.fill(ellipse);
			g2.draw(ellipse);
		}
    }

	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(unlock) {
			getThisTurnColor();
			
			point = e.getPoint();
			ellipseX = ((point.x-32)%36 <= 18) ? 36 * ((point.x-32)/36) +18 : 36 * ((point.x-32)/36+1)+18;
			ellipseY = ((point.y-32)%36 <= 18) ? 36 * ((point.y-32)/36) +18 : 36 * ((point.y-32)/36+1)+18;
			ellipse = new Ellipse2D.Double(ellipseX, ellipseY, 30, 30);
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(unlock) {
			//첫 흑돌은 정중앙에
			if(initNum >= neutralDolNum && badukalCount == 1) {
				ellipseX = 9*36+18;
				ellipseY = 9*36+18;
			}
			
			ellipsePoint = new Point(ellipseX, ellipseY);
			
			if(validatePoint()) {
				BadukalDB.points.push(ellipsePoint);
				BadukalDB.colors.push(thisTurnColor);
				setBoard();
				
				if(initNum < neutralDolNum) initNum++;
				else badukalCount++;
				System.out.println(badukalCount);
				repaint();
				
				judgeWinLose("B");
				judgeWinLose("W");
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void judgeWinLose(String c) {
		int win = 0;
		
		try {
			//행 검사
			for(int y=0; y<19; y++) {
				win = 0;
				for(int x=0; x<19; x++) {
					if(board[y][x].equals(c)) win+=1;
					else win=0;
					
					if(win == 6 && !board[y][x+1].equals(c)) {
						if(c.equals("B")) {
							System.out.println("검돌 행 승!");
							new BlackWinScreen();
						}
						if(c.equals("W")) {
							System.out.println("흰돌 행 승!");
							new WhiteWinScreen();
						}
						return;
					}
				}
			}
			
			//열 검사
			for(int x=0; x<19; x++) {
				win = 0;
				for(int y=0; y<19; y++) {
					if(board[y][x].equals(c)) win+=1;
					else win=0;
					
					if(win == 6 && !board[y+1][x].equals(c)) {
						if(c.equals("B")) {
							System.out.println("검돌 열 승!");
							new BlackWinScreen();
						}
						if(c.equals("W")) {
							System.out.println("흰돌 열 승!");
							new WhiteWinScreen();
						}
						return;
					}
				}
			}
			
			//대각선 검사 (왼쪽 위 시)
			for(int x=0; x<19; x++) {
				for(int i=0; i<19; i++) {
					try {
						if(board[0+i][x+i].equals(c)) win+=1;
						else win=0;
						
						if(win == 6 && !board[0+i+1][x+i+1].equals(c)) {
							if(c.equals("B")) {
								System.out.println("검돌 왼쪽 위 승!");
								new BlackWinScreen();
							}
							if(c.equals("W")) {
								System.out.println("흰돌 왼쪽 위 승!");
								new WhiteWinScreen();
							}
							return;
						}
					} catch (ArrayIndexOutOfBoundsException AIOOBe) {
						
					}
				}
			}
			for(int y=0; y<19; y++) {
				for(int i=0; i<19; i++) {
					try {
						if(board[y+i][0+i].equals(c)) win+=1;
						else win=0;
						
						if(win == 6 && !board[y+i+1][0+i+1].equals(c)) {
							if(c.equals("B")) {
								System.out.println("검돌 왼쪽 위 승!");
								new BlackWinScreen();
							}
							if(c.equals("W")) {
								System.out.println("흰돌 왼쪽 위 승!");
								new WhiteWinScreen();
							}
							return;
						}
					} catch (ArrayIndexOutOfBoundsException AIOOBe) {
						
					}
				}
			}
			
			//대각선 검사 (오른쪽 위 시)
			for(int x=0; x<19; x++) {
				for(int i=0; i<19; i++) {
					try {
						if(board[0+i][x-i].equals(c)) {
							win+=1;
						}
						else win=0;
						
						if(win == 6 && !board[0+i+1][x-i-1].equals(c)) {
							if(c.equals("B")) {
								System.out.println("x검돌 오른쪽 위 승!");
								new BlackWinScreen();
							}
							if(c.equals("W")) {
								System.out.println("x흰돌 오른쪽 위 승!");
								new WhiteWinScreen();
							}
							return;
						}
					} catch (ArrayIndexOutOfBoundsException AIOOBe) {
						
					}
				}
			}
			for(int y=0; y<19; y++) {
				for(int i=0; i<19; i++) {
					try {
						if(board[y+i][18-i].equals(c)) {
							win+=1;
						}
						else win=0;
						if(win == 6 && !board[y+i+1][18-i-1].equals(c)) {
							if(c.equals("B")) {
								System.out.println("y검돌 오른쪽 위 승!");
								new BlackWinScreen();
							}
							if(c.equals("W")) {
								System.out.println("y흰돌 오른쪽 위 승!");
								new WhiteWinScreen();
							}
							return;
						}
					} catch (ArrayIndexOutOfBoundsException AIOOBe) {
						
					}
				}
			}
			
		} catch (NullPointerException NPe) {
			
		}
		
	}
	
	
	public void setBoard() {
		if(thisTurnColor.equals(Color.GRAY)) board[ellipseY/(18*2)][ellipseX/(18*2)] = "N";
		else if(thisTurnColor.equals(Color.BLACK)) board[ellipseY/(18*2)][ellipseX/(18*2)] = "B";
		else board[ellipseY/(18*2)][ellipseX/(18*2)] = "W";
	}
	
	public boolean validatePoint() {
		for(int i=0; i<BadukalDB.points.size(); i++) {
			if(BadukalDB.points.get(i).equals(ellipsePoint)) {
				System.out.println("해당 위치에 이미 돌이 있습니다!");
				return false;
			}
		}
		return true;
	}
	
	public void getThisTurnColor() {
		if(initNum < neutralDolNum) thisTurnColor = Color.GRAY;
		else if (badukalCount/2%2 == 0) thisTurnColor = Color.BLACK;
		else if (badukalCount/2%2 != 0)thisTurnColor = Color.WHITE;
	}
	
}
