package lab3; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Program extends JFrame implements ActionListener  {
	GameBoard board;
	ScoreBoard score; 
	LatestRuns senasteSpel;
	HighScore bestPoang; 
	String initial; 
	NyaHighScores nyaHogaPoang;
	public Program() {
		this.setLayout(new BorderLayout());
		score = new ScoreBoard();
		board = new GameBoard(score);
		bestPoang = new HighScore (score); 
		senasteSpel = new LatestRuns(score); 
		add(score, BorderLayout.NORTH);
		score.getPreferredSize();
		add(board, BorderLayout.CENTER);
		historyScorePanel().setPreferredSize(new Dimension(Const.PrefListPanelXSize, 0)); 
		add(historyScorePanel(), BorderLayout.EAST); 
		setResizable(true);
		pack();
		setTitle("Breakout"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		score.setFocusable(false);
		board.grabFocus();
		board.start();
	}
	private JPanel historyScorePanel () {
		JPanel scorePanel = new JPanel(); 
		JButton restart = new JButton("RESTART");
		scorePanel.setLayout(new BorderLayout());
		bestPoang.setPreferredSize(new Dimension(Const.PrefListXSize, Const.PrefListYSize));
		senasteSpel.setPreferredSize(new Dimension (Const.PrefListXSize, Const.PrefListYSize));
		scorePanel.add(bestPoang, BorderLayout.CENTER); 
		scorePanel.add(senasteSpel, BorderLayout.NORTH);
		scorePanel.add(restart, BorderLayout.SOUTH);
		restart.addActionListener(this);
		bestPoang.setFocusable(false);
		senasteSpel.setFocusable(false);

		return scorePanel;
	}

	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		board.processKeyEvent(e);
	}

	public static void main(String[] args) {
		Program program = new Program();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		senasteSpel.nyttInput(score.getScore());
		board.getGame().pause();
		if(bestPoang.goodEnough(score.getScore())) {
			String initial = JOptionPane.showInputDialog("Skriv in 3 bokstäver");
			if (initial != null && initial.length() >= Const.InitialMax) {
				initial = initial.substring(0, Const.InitialMax); 
			} else if (initial != null && initial.length() == 2) {
				initial = initial.substring(0, 2); 
			} else if (initial != null) {
				initial = initial.substring(0, 1); 
			}
			nyaHogaPoang = new NyaHighScores(score.getScore(), initial); 
			bestPoang.addNewHighScore(nyaHogaPoang);
		}
		board.getGame().restart();
	}
}
