package lab3;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bat extends Sprite {

	private Color color; 
	public Bat() {
		super(Const.BatStartXPos, Const.BatYPos, Const.BatWidth, Const.BatHeight);
		this.color = Color.blue; 
	}
	public void bounceBallOnBat (Ball ball) {
		if (ball.getHitbox().intersects(getHitbox())) {
			ball.setY(ball.getY()-ball.getySpeed());
			if(ball.getX() + ball.getWidth()/2 > (getX() + 2 * getWidth() /Const.BatParts) && ball.getxSpeed() < 0) {
				//träff på högra tredjedel av bat och bollen kommer från höger 
				ball.setySpeed(ball.getySpeed()*(-1));
				ball.setxSpeed(ball.getxSpeed()*(-1)); 	
			} else if(ball.getX() + ball.getWidth()/2 > (getX() + 2 * getWidth() /Const.BatParts) && ball.getxSpeed() > 0) {
				//träff på högra tredjedel av bat och bollen kommer från vänster 
				ball.setySpeed(ball.getySpeed()*(-1));	
			} else if(ball.getX() + ball.getWidth()/2 < (getX() + getWidth() /Const.BatParts) && ball.getxSpeed() < 0) {
				//träff på vänstra halvan av bat och bollen kommer från höger
				ball.setySpeed(ball.getySpeed()*(-1));
			} else if (ball.getX() + ball.getWidth()/2 < (getX() + getWidth() /Const.BatParts) && ball.getxSpeed() > 0) {
				//träff på vänstra halvan av bat och bollen kommer från vänster 
				ball.setySpeed(ball.getySpeed()*(-1));
				ball.setxSpeed(ball.getxSpeed()*(-1)); 	
			} else if (ball.getX() + ball.getWidth()/2 > (getX() + getWidth() /Const.BatParts) && ball.getX() <(getX() + 2 * getWidth() /Const.BatParts) ) {
				//träff mitt i 
				ball.setxSpeed(0);
				ball.setySpeed(ball.getySpeed()*(-1));
			} else if (ball.getxSpeed() == 0 && ball.getX() + ball.getWidth()/2 < (getX() + getWidth() /Const.BatParts)) {
				// träff på vänster sida, bollen kommer rakt ner
				ball.setxSpeed(Const.BallSpeed);
			} else if (ball.getxSpeed() == 0 && ball.getX() + ball.getWidth()/2 > (getX() + 2 * getWidth() /Const.BatParts)) {
				// träff på höger sida, bollen kommer rakt ner
				ball.setxSpeed(-Const.BallSpeed);
			}
				
		}
	}
	
	@Override
	public void update(Keyboard keyboard) {
		if (keyboard.isKeyDown(Key.Left)) {
			setX(getX()-Const.BatSpeed); 
		} 
		if (keyboard.isKeyDown(Key.Right)) {
			setX(getX()+Const.BatSpeed); 
		} 
		if (getX() + Const.BatWidth < 0)
			setX(Const.PrefXDimension);
		if (getX() > Const.PrefXDimension)
			setX(0 - Const.BatWidth);
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
		graphics.setColor(color.WHITE);
		graphics.drawString("HB", getX() + getWidth() /Const.BatParts, getY() + getHeight());


	}

}
