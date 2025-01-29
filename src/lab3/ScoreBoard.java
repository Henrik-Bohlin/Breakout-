package lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ScoreBoard extends JPanel   {
	private int pointCount = 0;
	private int lifeCount = Const.Lifes; 
	private String text;
	private JLabel poang;
	private JLabel liv; 
	public ScoreBoard () {
		setLayout(new GridLayout (1, Const.ScoreBoardParts));
		poang = new JLabel (); 
		JLabel gameTitle = new JLabel ("BREAKOUT TO PARADISE"); 
		liv = new JLabel(); 
		add(poang); 
		add(gameTitle);
		add(liv); 
	}
	public int getScore () {
		return pointCount; 
	}
	public void setScore (int score) {
		this.pointCount = score; 
	}
	public void yellowPoint () {
		this.pointCount = pointCount + Const.YellowPoint; 
	}
	public void orangePoint () {
		this.pointCount = pointCount + Const.OrangePoint; 
	}
	public void redPoint () {
		this.pointCount = pointCount + Const.RedPoint; 
	}
	public int getLifes () {
		return lifeCount; 
	}

	public void setLifes (int life) {
		this.lifeCount = life; 

	}
	public void update (Keyboard keyboard) {

	}
	public void draw (Graphics2D graphics) {
		poang.setText("Score: " + pointCount );
		liv.setText("Lifes: " + lifeCount);
	}
	public void drawWinner(Graphics2D graphics) {
		graphics.setColor(Color.PINK);
		graphics.setFont(new Font("Courier new", Font.CENTER_BASELINE, Const.EndingMessageFontSize));
		graphics.drawString("Welcome to Paradise", Const.EndingOutputXPos, Const.EndingMessageYPos);
		graphics.setFont(new Font("Courier new", Font.CENTER_BASELINE, Const.EndingScoreFontSize));
		graphics.drawString("Your score: " + pointCount, Const.EndingOutputXPos, Const.EndingScoreYPos);
	}
	public void drawLoser(Graphics2D graphics) {
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Courier new", Font.CENTER_BASELINE, Const.EndingMessageFontSize));
		graphics.drawString("Game over", Const.EndingOutputXPos, Const.EndingMessageYPos);
		graphics.setFont(new Font("Courier new", Font.CENTER_BASELINE, Const.EndingScoreFontSize));
		graphics.drawString("Your score: " + pointCount, Const.EndingOutputXPos, Const.EndingScoreYPos);
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Const.ScoreBoardPrefX, Const.ScoreBoardPrefY);
	}
}
