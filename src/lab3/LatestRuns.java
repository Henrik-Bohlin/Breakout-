package lab3;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LatestRuns extends JPanel {
	private JList latestRunsList;
	private DefaultListModel latestRunsListModel; 
	private ScoreBoard scoreboard; 
	private JLabel titel;
	
	public LatestRuns (ScoreBoard scoreboard) {
		setLayout(new BorderLayout()); 
		this.scoreboard = scoreboard; 
		titel = new JLabel("LATEST RUNS");
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		latestRunsListModel = new DefaultListModel(); 
		latestRunsList = new JList(latestRunsListModel); 
		latestRunsList.setFocusable(false);
		add(titel, BorderLayout.NORTH); 
		add(latestRunsList, BorderLayout.CENTER); 
	}
	
	public void nyttInput (int score) {
		if (latestRunsListModel.size() == 3) {
			latestRunsListModel.remove(2);
		}
		for (int i=latestRunsListModel.size();i<0;i--) {
			latestRunsListModel.set(i, latestRunsListModel.get(i-1)); 
		}
		int tmp = scoreboard.getScore();
		String poang = Integer.toString(tmp);
		latestRunsListModel.add(0, poang);
		}
}