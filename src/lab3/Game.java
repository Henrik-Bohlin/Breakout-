package lab3; 

import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Game {
	private Ball ball; 
	private Bat bat;
	private BrickCollection brickor;
	private ScoreBoard score;
	private LatestRuns latestRuns;
	private GameBoard board;
	public Game(GameBoard board, ScoreBoard score) {
		ball = new Ball(); 
		bat = new Bat(); 
		this.score = score; 
		latestRuns = new LatestRuns(score);
		brickor = new BrickCollection();
		this.board = board;
	}

	public void update(Keyboard keyboard) {
		ball.update(keyboard);
		bat.update(keyboard);
		brickor.update(keyboard);
		score.update(keyboard);
		bat.bounceBallOnBat(ball); 
		if (brickor.yellowBrickHit(ball)) {
			brickor.bounceBallOnBrick(ball);
			score.yellowPoint();
		}
		if (brickor.orangeBrickHit(ball)) {
			brickor.bounceBallOnBrick(ball);
			score.orangePoint();
		}
		if (brickor.redBrickHit(ball)) {
			brickor.bounceBallOnBrick(ball);
			score.redPoint();
		}	
		if (ball.dead()) {
			if (score.getLifes() > 1) {
				score.setLifes(score.getLifes()-1);
				ball.setX(bat.getX()); 
				ball.setY(bat.getY() - 2 * Const.BatHeight);
			} else if (score.getLifes() == 1) {
				score.setLifes(score.getLifes()-1);
			}
		}
		if (keyboard.isKeyDown(Key.Space) && ball.getY() == bat.getY()-Const.BrickHeight) {
			ball.setySpeed(-Const.BallSpeed);
		}
	}
	public void draw(Graphics2D graphics) {
		ball.draw(graphics);
		bat.draw(graphics);
		brickor.draw(graphics);
		if (score.getLifes() == 0 && !brickor.allBricksGone()) {
			score.drawLoser(graphics);
		}
		if (brickor.allBricksGone()) {
			score.drawWinner(graphics); 
		}
		score.draw(graphics);
	}
	public void restart () {
		score.setLifes(Const.Lifes);
		score.setScore(0);
		brickor.refresh();
		board.grabFocus();
		ball.goToStartPos(bat);
	}
	public void pause () {
		ball.freeze();
	}
}
