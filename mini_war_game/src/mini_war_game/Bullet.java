package mini_war_game;
import java.awt.*;

	public class Bullet {
	    private int x, y;
	    private boolean visible = true;
	    private final player.Direction direction;

	    public Bullet(int x, int y, player.Direction direction) {
	        this.x = x;
	        this.y = y;
	        this.direction = direction;
	    }

	    public void move() {
	        switch (direction) {
	            case RIGHT -> x += 10;
	            case LEFT -> x -= 10;
	            case NORTH_EAST -> { x += 7; y -= 7; }
	            case NORTH_WEST -> { x -= 7; y -= 7; }
	        }
	    }

	    public void draw(Graphics g) {
	        g.setColor(Color.RED);
	        g.fillOval(x, y, 10, 10);
	    }

	    public int getX() { return x; }
	    public int getY() { return y; }
	    public boolean isVisible() { return visible; }
	    public void setVisible(boolean visible) { this.visible = visible; }
	}


