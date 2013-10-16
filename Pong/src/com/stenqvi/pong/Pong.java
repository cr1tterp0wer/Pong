package com.stenqvi.pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Pong extends JPanel implements Runnable, ActionListener
{

	static JFrame jframe;
	static boolean bGame = true;
	static Thread t;
	static Graphics2D g2d;
	private static GamePanel gamePanel;

	public static void main(String[] args)
	{
		t = new Thread(new Pong());
		t.start();
		System.out.println("main()");
	}

	@Override
	public void run()
	{
		init();
		loadContent();

		gameLoop();
	}

	public static void init()
	{
		gamePanel = new GamePanel();
		gamePanel.setFocusable(true);

		jframe = new JFrame("PONG");
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(800,600));
		jframe.add(gamePanel);
		jframe.pack();
		jframe.setVisible(true);
		System.out.println("init()");


	}
	//LoadContent() loads all non-game logic essentials: audio, images, etc..
	//  from the Content folder
	public static void loadContent()
	{
		System.out.println("loadComponent()");
	}


	public void gameLoop()
	{
		//update and render the PANEL not the JFrame/Container
		while(true)
		{
			update();
			gamePanel.repaint();
			try{Thread.sleep(10);} 
			catch(Exception e){}
		}
	}

	public static void update()
	{
		gamePanel.update();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}


class GamePanel extends JPanel implements KeyListener
{
	static enum Position{up,down,DEFUALT};
	private Position padPosition01, padPosition02;

	boolean spacePressed = false;
	int x = 0;
	int y = 0;
	CollisionObject paddle01, paddle02;
	Ball ball;

	public GamePanel()
	{
		padPosition01= padPosition02 = Position.DEFUALT;

		this.addKeyListener(this);
		paddle01 = new CollisionObject(0,0,20, 150);
		paddle02 = new CollisionObject(765,0,20, 150);		   
		ball = new Ball(paddle01.getX()+paddle01.getWidth(), paddle01.getY(),15,15);
		ball.follow(paddle01.getRect());
	}


	//UPDATE
	public void update()
	{
		//logic here
		paddle01.update(padPosition01.ordinal());
		paddle02.update(padPosition02.ordinal());
		ball.update(paddle01.getRect(), paddle02.getRect(), spacePressed);

	}

	//RENDER
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		paddle01.draw(g);
		paddle02.draw(g);
		ball.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W: padPosition01 = Position.up;
		break;
		case KeyEvent.VK_S: padPosition01 = Position.down;
		break;
		case KeyEvent.VK_UP: padPosition02 = Position.up;
		break;
		case KeyEvent.VK_DOWN: padPosition02 = Position.down;
		break;
		case KeyEvent.VK_SPACE: spacePressed = true;
		default: break;
		}
		
	
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP)
			padPosition02 = Position.DEFUALT;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			padPosition02 = Position.DEFUALT;

		if(e.getKeyCode() == KeyEvent.VK_W)
			padPosition01 = Position.DEFUALT;
		if(e.getKeyCode() == KeyEvent.VK_S)
			padPosition01 = Position.DEFUALT;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			spacePressed = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}




