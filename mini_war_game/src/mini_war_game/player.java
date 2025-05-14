package mini_war_game;
import java.awt.Color;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class player extends JPanel{
            private int playerX, playerY;
		    private final int playerWidth = 20, playerHeight = 20;
		    private final int bulletWidth = 10, bulletHeight = 10;
		    private ArrayList<Bullet> bullets;
		    private MonsterManager monsterManager;
		  

		    private Direction weaponDirection = Direction.RIGHT;

		    public enum Direction { RIGHT, LEFT, NORTH_EAST, NORTH_WEST }

		    public player(int width, int height, MonsterManager monsterManager) {
		        this.playerX = (width - playerWidth) / 2;
		        this.playerY = height - 80;
		        this.bullets = new ArrayList<>();
		        this.monsterManager = monsterManager;

		        addKeyListener(new KeyAdapter() {
		            public void keyPressed(KeyEvent e) {
		                switch (e.getKeyCode()) {
		                    case KeyEvent.VK_LEFT -> { playerX -= 7; weaponDirection = Direction.LEFT; }
		                    case KeyEvent.VK_RIGHT -> { playerX += 7; weaponDirection = Direction.RIGHT; }
		                    case KeyEvent.VK_UP -> {
		                        if (weaponDirection == Direction.RIGHT) weaponDirection = Direction.NORTH_EAST;
		                        else if (weaponDirection == Direction.LEFT) weaponDirection = Direction.NORTH_WEST;
		                    }
		                    case KeyEvent.VK_DOWN -> {
		                        if (weaponDirection == Direction.NORTH_EAST) weaponDirection = Direction.RIGHT;
		                        else if (weaponDirection == Direction.NORTH_WEST) weaponDirection = Direction.LEFT;
		                    }
		                    case KeyEvent.VK_SPACE -> shoot();
		                }
		                repaint();
		            }
		        });
		        setFocusable(true);
		    }

		    public void shoot() {
		        int bulletX = playerX + playerWidth / 2 - bulletWidth / 2;
		        int bulletY = playerY - bulletHeight;

		        switch (weaponDirection) {
		            case RIGHT -> bulletX = playerX + playerWidth;
		            case LEFT -> bulletX = playerX - bulletWidth;
		            case NORTH_EAST -> { bulletX = playerX + playerWidth; bulletY -= 10; }
		            case NORTH_WEST -> { bulletX = playerX - bulletWidth; bulletY -= 10; }
		        }

		        bullets.add(new Bullet(bulletX, bulletY, weaponDirection));
		    }

		    public void moveBullets() {
		        for (Bullet b : bullets) {
		            b.move();
		            monsterManager.checkCollision(b);
		           

		        }
		    }

		   
	            

			protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.setColor(Color.WHITE);
		        g.fillRect(playerX, playerY, playerWidth, playerHeight);
		        g.setColor(Color.BLACK);
		        g.drawRect(playerX, playerY, playerWidth, playerHeight);
		        g.setColor(Color.WHITE);
		        g.fillOval(playerX, playerY - 20, playerWidth, 20);
		        g.setColor(Color.BLACK);
		        g.drawOval(playerX, playerY - 20, playerWidth, 20);
		        
		        
		        g.setColor(new Color(178, 216, 230));
		        g.fillRect(playerX, playerY + playerHeight, 5, 10);
		        g.fillRect(playerX + playerWidth - 5, playerY + playerHeight, 5, 10);
		        
		        
		        
		     // Dessiner l'arme
		        g.setColor(Color.GRAY);
		        int gunWidth = 5;
		        int gunHeight = 30;
		        int gunX = playerX + playerWidth / 2 - gunWidth / 2;
		        int gunY = playerY + 25;

		        switch (weaponDirection) {
		            case RIGHT -> {
		                gunX = playerX + playerWidth;
		                gunY = playerY - 10;
		                g.fillRect(gunX, gunY, gunWidth, gunHeight);
		            }
		            case LEFT -> {
		                gunX = playerX - gunWidth;
		                gunY = playerY - 10;
		                g.fillRect(gunX, gunY, gunWidth, gunHeight);
		            }
		            case NORTH_EAST -> {
		                gunX = playerX + playerWidth;
		                gunY = playerY - 20;
		                g.fillRect(gunX, gunY, gunHeight, gunWidth); // Vertical en biais
		            }
		            case NORTH_WEST -> {
		                gunX = playerX - gunHeight;
		                gunY = playerY - 20;
		                g.fillRect(gunX, gunY, gunHeight, gunWidth);
		            }
		        }

		        for (Bullet b : bullets)
		            if (b.isVisible()) b.draw(g);
		        monsterManager.draw(g);
		    }
		}
