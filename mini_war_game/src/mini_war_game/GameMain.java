package mini_war_game;


	import javax.swing.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class GameMain {
	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Mini War Game");
	        frame.setSize(800, 600);
	        MonsterManager monsterManager = new MonsterManager(800, 600);
	        player gamePanel = new player(800, 600, monsterManager);
	        frame.add(gamePanel);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);

	        Timer timer = new Timer(30, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                gamePanel.moveBullets();
	                monsterManager.move(600, 800);
	                gamePanel.repaint();
	            }
	        });
	        timer.start();
	    }
	}

