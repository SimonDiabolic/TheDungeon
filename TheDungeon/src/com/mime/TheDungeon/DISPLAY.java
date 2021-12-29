package com.mime.TheDungeon;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mime.TheDungeon.graphics.RENDER;
import com.mime.TheDungeon.graphics.SCREEN;

public class DISPLAY extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int width = 800;
	public static final int height = 600;
	public static final String title = "TheDungeon";

	private Thread thread;
	private boolean running = false;
	private SCREEN screen;
	private BufferedImage img;
	private int pixels[];
	private RENDER render;

	public DISPLAY() {
		screen = new SCREEN(width, height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
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
		while (running) {
			tick();
			render();
		}
	}

	private void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.render();
		
		for (int i = 0; i < width * height; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();
		bs.show();
	}

	private void tick() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		DISPLAY game = new DISPLAY();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		game.start();

	}

}
