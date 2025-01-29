package lab3;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighScore extends JPanel{
	private JList<NyaHighScores> highScoreListView;
	private ArrayList<NyaHighScores> highScoreList; 
	private DefaultListModel <NyaHighScores> highScoreListModel; 
	private ScoreBoard scoreboard;
	private JLabel titel;

	public HighScore (ScoreBoard scoreboard)  {
		setLayout(new BorderLayout()); 
		this.scoreboard = scoreboard; 
		titel = new JLabel("HIGH SCORE"); 
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		highScoreListModel = new DefaultListModel<NyaHighScores>(); 
		highScoreList = new ArrayList<NyaHighScores>(); 
		highScoreListView = new JList<NyaHighScores>(highScoreListModel); 

		add(highScoreListView, BorderLayout.CENTER);
		add(titel, BorderLayout.NORTH); 

	}
	public boolean isFull () {
		if (highScoreListModel.getSize() >= Const.HighScoreListSize) {
			return true; 
		} return false; 
	}

	public boolean goodEnough ( int score) {
		if (!isFull()) {
			return true; 
		}
		if (isFull() && score > highScoreListModel.get(highScoreListModel.getSize()-1).getScore()) {
			return true; 
		}
		return false;
	}
	public void addNewHighScore (NyaHighScores nya) {
		highScoreList.add(nya); 
		int n = highScoreList.size();
		for (int i=0; i<n-1;i++) {
			for (int j=0;j<n-1-i;j++) {
				if (highScoreList.get(j).score < highScoreList.get(j+1).score) {
					NyaHighScores temp = highScoreList.get(j+1);
					highScoreList.set(j+1, highScoreList.get(j));
					highScoreList.set(j, temp); 
				}
			}
		}
		if (highScoreList.size() > Const.HighScoreListSize) {
            highScoreList.remove(highScoreList.size() - 1);
        }
		highScoreListModel.clear();
		highScoreList.forEach(highScoreListModel::addElement);
	}
}
