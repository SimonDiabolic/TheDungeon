package com.mime.TheDungeon;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mime.TheDungeon.graphics.RENDER;
import com.mime.TheDungeon.graphics.SCREEN;
import com.mime.TheDungeon.input.INPUTHANDLER;

public class DISPLAY extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int width = 800;
	public static final int height = 600;
	public static final String title = "TheDungeon";

	private Thread thread;
	private boolean running = false;
	private SCREEN screen;
	private GAME game;
	private BufferedImage img;
	private int pixels[];
	private INPUTHANDLER input;

	public DISPLAY() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		screen = new SCREEN(width, height);
		game = new GAME();
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		
		input = new INPUTHANDLER();
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
	}

	private void start() {
		if (running)
			return;
		else {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	private void stop() {
		if (!running)
			return;
		else
			running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void run() {
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;

		while (running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;

			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if (tickCount % 60 == 0) {
					System.out.println(frames + " FPS");
					previousTime += 1000;
					frames = 0;

				}
			}
			if (ticked) {
				render();
				frames++;
			}
			render();
			frames++;
		}
	}

	private void render() {
		//
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.render(game);

		for (int i = 0; i < width * height; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();
		bs.show();
	}

	private void tick() {
		game.tick(input.key);

	}

	public static void main(String[] args) {
		DISPLAY game = new DISPLAY();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		game.start();

	}

}
