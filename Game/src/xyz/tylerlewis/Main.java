package xyz.tylerlewis;

import java.awt.Canvas;

public class Main extends Canvas implements Runnable {
	private boolean running = false;
	private Thread gameThread;
	private final String TITLE = "Game";
	
	public Main() {
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
		
	}
	public void tick() {
		
	}

	public static void main(String [] args) {
		new Main();
	}
	
}
