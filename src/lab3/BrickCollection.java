package lab3;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickCollection {
	private ArrayList <Brick> allaBrickor; 
	private ArrayList <Brick> skrap; 
	public BrickCollection () {
		allaBrickor = new ArrayList <Brick> ();
		skrap = new ArrayList <Brick> (); 
		for (int i=0; i<Const.BricksPerRow;i++) {
			allaBrickor.add(new RedBrick((Const.DistanceBetweenBricks*i+Const.InsertedDistanceFromTheWall),Const.RedBricksStartYPos));
			allaBrickor.add(new OrangeBrick((Const.DistanceBetweenBricks*i+Const.InsertedDistanceFromTheWall),Const.OrangeBricksStartYPos));
			allaBrickor.add(new YellowBrick((Const.DistanceBetweenBricks*i+Const.InsertedDistanceFromTheWall),Const.YellowBricksStartYPos));
		}
	}
	public boolean yellowBrickHit (Ball ball) {
		for (int i=0; i<allaBrickor.size();i++) {
			if (allaBrickor.get(i).getHitbox().intersects(ball.getHitbox())) {
				if (allaBrickor.get(i).getColor() == Color.yellow) {
					skrap.add(allaBrickor.get(i)); 
					return true; 	
				}
			}
		}
		allaBrickor.removeAll(skrap);
		return false; 
	}
	public boolean orangeBrickHit (Ball ball) {
		for (int i=0; i<allaBrickor.size();i++) {
			if (allaBrickor.get(i).getHitbox().intersects(ball.getHitbox())) {
				if (allaBrickor.get(i).getColor() == Color.orange) {
					allaBrickor.get(i).setColor(Color.yellow);
					return true; 	
				}
			}
		}
		return false; 
	}

	public boolean redBrickHit (Ball ball) {
		for (int i=0; i<allaBrickor.size();i++) {
			if (allaBrickor.get(i).getHitbox().intersects(ball.getHitbox())) {
				if (allaBrickor.get(i).getColor() == Color.red) {
					allaBrickor.get(i).setColor(Color.orange);
					return true; 	
				}
			}
		}
		return false; 
	}
	public void refresh () {
		for (int j=0; j<allaBrickor.size();j++) {
			skrap.add(allaBrickor.get(j));
		}
		allaBrickor.remove(skrap);
		for (int i=0; i<Const.BricksPerRow;i++) {
			allaBrickor.add(new RedBrick((Const.DistanceBetweenBricks*i+Const.InsertedDistanceFromTheWall),Const.RedBricksStartYPos));
			allaBrickor.add(new OrangeBrick((Const.DistanceBetweenBricks*i+Const.InsertedDistanceFromTheWall),Const.OrangeBricksStartYPos));
			allaBrickor.add(new YellowBrick((Const.DistanceBetweenBricks*i+Const.InsertedDistanceFromTheWall),Const.YellowBricksStartYPos));
		}
	}


	public boolean allBricksGone () {
		if (allaBrickor.size()== 0) {
			return true; 
		}
		return false; 
	}

	public void bounceBallOnBrick (Ball ball) {
		for (int i=0; i<allaBrickor.size();i++) {
			if (allaBrickor.get(i).getHitbox().intersects(ball.getHitbox())) {
				if (ball.isMovingLeft() && ball.isMovingUp()) {
					if (ball.isUnder(allaBrickor.get(i).getY())) {
						ball.setY(ball.getY()+Const.BounceDistance);
						ball.setySpeed(ball.getySpeed()*-1);
					} else if (ball.isRight(allaBrickor.get(i).getX())) {
						ball.setX(ball.getX()+Const.BounceDistance);
						ball.setxSpeed(ball.getxSpeed()*-1);
					}
				}
				else if (ball.isMovingRight() && ball.isMovingUp()) {
					if (ball.isUnder(allaBrickor.get(i).getY())) {
						ball.setY(ball.getY()+Const.BounceDistance);
						ball.setySpeed(ball.getySpeed()*-1);
					} else if (ball.isLeft(allaBrickor.get(i).getX())) {
						ball.setX(ball.getX()-Const.BounceDistance);
						ball.setxSpeed(ball.getxSpeed()*-1);
					}
				}
				if (ball.isMovingLeft() && ball.isMovingDown()) {
					if (ball.isOver(allaBrickor.get(i).getY())) {
						ball.setY(ball.getY()-Const.BounceDistance);
						ball.setySpeed(ball.getySpeed()*-1);
					} else if (ball.isRight(allaBrickor.get(i).getX())) {
						ball.setX(ball.getX()+Const.BounceDistance);
						ball.setxSpeed(ball.getxSpeed()*-1);
					}
				}
				else if (ball.isMovingRight() && ball.isMovingDown()) {
					if (ball.isOver(allaBrickor.get(i).getY())) {
						ball.setY(ball.getY()-Const.BounceDistance);
						ball.setySpeed(ball.getySpeed()*-1);
					} else if (ball.isLeft(allaBrickor.get(i).getX())) {
						ball.setX(ball.getX()-Const.BounceDistance);
						ball.setxSpeed(ball.getxSpeed()*-1);
					}
				}
				if (ball.isMovingStraight()) {
					if (ball.isOver(allaBrickor.get(i).getY())) {
						ball.setY(ball.getY()-ball.getySpeed());
						ball.setySpeed(ball.getySpeed()*-1);
					} else if (ball.isUnder(allaBrickor.get(i).getY())) {
						ball.setY(ball.getY()+Const.BounceDistance);
						ball.setySpeed(ball.getySpeed()*-1);
					}
				}
			}
		}
	}
	public void update (Keyboard keyboard) {
		for (int i=0; i<allaBrickor.size();i++) {
			allaBrickor.get(i).update(keyboard);

		}
	}
	public void draw (Graphics2D graphics) {
		for (int i=0; i<allaBrickor.size();i++) {
			allaBrickor.get(i).draw(graphics);
		}

	}

}
