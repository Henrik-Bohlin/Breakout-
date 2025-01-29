package lab3;

import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Sprite {
	private Color color; 
	public Brick(int x, int y, Color color) {
		super(x, y, Const.BrickWidth, Const.BrickHeight);
		this.color = color; 
	}
	public Color getColor () {
		return this.color; 
	}
	public void setColor (Color color) {
		this.color = color; 
	}
	
	@Override
	public void update(Keyboard keyboard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
