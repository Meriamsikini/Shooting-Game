package mini_war_game;
import java.awt.*;


	public class MonsterManager {
	    private int[] monsterX = new int[20];
	    private int[] monsterY = new int[20];
	    private boolean[] monsterAlive = new boolean[20];
	    private final double monsterSpeed = 1;
	    

	    public MonsterManager(int width, int height) {
	        int spacing = width / 15;
	        for (int i = 0; i < 20; i++) {
	            monsterX[i] = i * spacing;
	            monsterY[i] = (int) (Math.random() * (height / 3));
	            monsterAlive[i] = true;
	            
	        }
	        
	    }

	    public void move(int height, int width) {
	        for (int i = 0; i < 20; i++) {
	            if (monsterAlive[i]) {
	                monsterY[i] += monsterSpeed;
	                if (monsterY[i] > height) {
	                    monsterY[i] = 0;
	                    monsterX[i] = (int) (Math.random() * width);
	                }
	            }
	        }
	    }

	    public void draw(Graphics g) {
	
	                for (int i = 0; i < 20; i++) {
	    	            if (monsterAlive[i]) {
	    	                g.setColor(Color.RED);
	    	                g.fillRect(monsterX[i], monsterY[i], 20, 30);
	    	                g.setColor(new Color(102, 51, 0));
	    	                g.fillOval(monsterX[i], monsterY[i] - 20, 20, 20);
	    	                g.fillRect(monsterX[i] + 20, monsterY[i] + 10, 5, 5);
	    	                g.fillRect(monsterX[i] - 5, monsterY[i] + 10, 5, 5);
	    	                g.fillRect(monsterX[i] + 12, monsterY[i] + 30, 6, 20); // pied gauche
	    	                g.fillRect(monsterX[i] +1, monsterY[i] + 30, 6, 20); // pied droite
	    	             }
	    	        }
	            }
	        
	   

	    public void checkCollision(Bullet bullet) {
	        for (int i = 0; i < 20; i++) {
	            if (monsterAlive[i] &&
	                bullet.getX() >= monsterX[i] && bullet.getX() <= monsterX[i] + 20 &&
	                bullet.getY() >= monsterY[i] && bullet.getY() <= monsterY[i] + 50) {
	                monsterAlive[i] = false;
	                bullet.setVisible(false);
	            }
	        }
	    }
	 
	    }
	


