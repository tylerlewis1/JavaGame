package xyz.tylerlewis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private boolean running = false;
	private Thread gameThread;
	private final String TITLE = "Game";
	public JFrame frame;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 300;
	private int height = width / 16 * 9;
	private double scale = (screenSize.getHeight() / height);
	
	
	public Main() {
		Dimension windowSize = new Dimension((int)(width * scale), (int)(height * scale)); 
		setPreferredSize(windowSize);
		frame = new JFrame(TITLE);
		frame.setSize(windowSize);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		start();
	}
	
	private synchronized void start() {
		running = true;
		gameThread = new Thread(this, TITLE);
		gameThread.start();
	}
	private synchronized void stop() {
		try {
			gameThread.join();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void run() {
		long lastTime = System.nanoTime();
		long secTimer = System.currentTimeMillis();
		double ns =  1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		while(running) {
			long now = System.nanoTime();
			delta += ((now - lastTime) / ns);
			lastTime = now;
			while(delta >= 1) {
				ticks++;
				tick();
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - secTimer > 1000) {
				secTimer = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "\nTicks: " + ticks + "\n");
				ticks = 0;
				frames = 0;
			}
		}
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//draw here
		g.setColor(Color.blue);
		g.fillRect(0, 0, (int)(width*scale), (int)(height*scale));
		//----------
		g.dispose();
		bs.show();
		
	}
	public void tick() {
		
	}

	public static void main(String [] args) {
		new Main();
	}
	
}
