package lab3;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Sprite{
	private int xSpeed;
	private int ySpeed; 
	private Color color; 
	public Ball () {
		super(Const.BallStartXPos, Const.BallStartYPos, Const.BallWidth, Const.BallHeight);
		this.color = Color.black; 
		setxSpeed(Const.BallSpeed);
		setySpeed(-Const.BallSpeed); 
	}
	public void update(Keyboard keyboard) {	
		setY(getY()+getySpeed());
		setX(getX()+getxSpeed()); 
		if (getX()>= Const.RightWall || getX()<= Const.LeftWall) {
			setxSpeed(-getxSpeed());
		}
		if (getY()<= 0) {
			setY(getY()+Const.BounceDistance);
			setySpeed(-getySpeed()); 
		}
	}
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	public void goToStartPos(Bat bat) {
		setX(bat.getX());
		setY(bat.getY()-Const.BrickHeight);
		setySpeed(0);
		setxSpeed(0);
	}
	public void freeze () {
		setySpeed(0);
		setxSpeed(0);
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	public boolean isUnder (int y) {
		if (getY() > y + Const.BrickHeight - Const.InnerHitbox) {
			return true; 
		}
		return false;
	}
	public boolean isOver (int y) {
		if (getY() + Const.BallHeight < y + Const.InnerHitbox) {
			return true; 
		}
		return false;
	}
	public boolean isRight (int x) {
		if (getX() > x + Const.BrickWidth - Const.InnerHitbox) {
			return true; 
		}
		return false;
	}
	public boolean isLeft (int x) {
		if (getX() + Const.BallWidth < x + Const.InnerHitbox ) {
			return true; 
		}
		return false;
	}
	public boolean isMovingUp () {
		if (ySpeed < 0) {
			return true;
		}
		return false;
	}
	public boolean isMovingDown () {
		if (ySpeed > 0) {
			return true;
		}
		return false;
	}
	public boolean isMovingRight () {
		if (xSpeed > 0) {
			return true;
		}
		return false;
	}
	public boolean isMovingLeft () {
		if (xSpeed < 0) {
			return true;
		}
		return false;
	}
	public boolean isMovingStraight () {
		if (xSpeed == 0) {
			return true; 
		} 
		return false;
	}
	public boolean dead () {
		if (getY() > Const.Floor) {
			return true; 
		}
		return false; 
	}
}
