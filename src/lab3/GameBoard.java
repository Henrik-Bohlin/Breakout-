package lab3;  

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class GameBoard extends JComponent {
	private final int FPS = 40; 
	private Game game;
	private Keyboard keyboard;
	private Image backgroundImage; 

	public GameBoard(ScoreBoard score) {
		keyboard = new Keyboard();
		game = new Game(this, score);
		backgroundImage = Toolkit.getDefaultToolkit().getImage("beach.png");
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Const.PrefXDimension, Const.PrefYDimension);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D graphics = (Graphics2D)arg0;
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
		game.draw(graphics);
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		if (e.getID() == KeyEvent.KEY_PRESSED)
			keyboard.processKeyEvent(e.getKeyCode(), true);
		else if (e.getID() == KeyEvent.KEY_RELEASED)
			keyboard.processKeyEvent(e.getKeyCode(), false);
	}
	public Game getGame () {
		return game; 
	}
	public void start() {
		while(true) {
			game.update(keyboard);
			try {
				Thread.sleep(1000 / FPS); //Throttle thread
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
