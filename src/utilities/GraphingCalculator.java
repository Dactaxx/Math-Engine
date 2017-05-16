package utilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.mariuszgromada.math.mxparser.Function;

import static mathengine.MathEngine.graphics;

import graphics.GraphicsThread;
import mathengine.MathEngine;

public class GraphingCalculator extends GraphicsThread implements KeyListener {
	private double xOffset = 0;
	private double yOffset = 0;
	private String function = "x";
	private int cursorBlink = 0;
	public GraphingCalculator()	{
		MathEngine.gis.add(this);
		this.setRunState(GraphicsThread.RUNNING);
		MathEngine.graphics.update(MathEngine.gis);
		MathEngine.graphics.frame.addKeyListener(this);
		
	}
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillRect(0, 0, (int)(1920 * graphics.scale), (int)(1080 * graphics.scale));

		g2d.setColor(new Color(200, 200, 200));
		drawGrid(g2d);
		
		g2d.setColor(new Color(0, 0, 0));
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine((int)((960 + xOffset) * graphics.scale),  0,  (int)((960 + xOffset) * graphics.scale),1080);
		g2d.drawLine(0, (int)((540 + yOffset) * graphics.scale), 1920, (int)((540 + yOffset) * graphics.scale));
		
		g2d.setStroke(new BasicStroke(5));
		graph(g2d);
		
		textArea(g2d);
		
	}

	private void drawGrid(Graphics2D g2d) {
		for(int i = 20; i < 1080; i += 40) {
			g2d.drawLine((int)(0), (int)((i + yOffset >= 0 ? Math.abs((i + yOffset) % 1080) : 1080 - Math.abs((i + yOffset) % 1080)) * graphics.scale), (int)(1920), (int)((i + yOffset >= 0 ? Math.abs((i + yOffset) % 1080) : 1080 - Math.abs((i + yOffset) % 1080)) * graphics.scale));
		}
		
		for(int i = 0; i < 1920; i += 40) {
			g2d.drawLine(i + xOffset >= 0 ? Math.abs((int)(i + xOffset) % 1920) : 1920 - Math.abs((int)(i + xOffset) % 1920), (int)(0), i + xOffset >= 0 ? Math.abs((int)(i + xOffset) % 1920) : 1920 - Math.abs((int)(i + xOffset) % 1920), (int)(1080));
		}
	}
	
	private void graph(Graphics2D g2d) {
		double oldX  = .112358, oldY = .112358;
		g2d.setColor(new Color(0, 0, 255));
		
		Function f = new Function("f(x) = " + function);
		
		for(double x = -xOffset / 40 - 24; x < -xOffset / 40 + 24; x += .02) {
			double y;
			y = f.calculate(x);
//			y = (x - 3) * (x - 2) * (x - 1) * x * (x +1) * (x + 2) * (x + 3) * .1;
			
			if(oldX == .112358) {
				oldX = x;
				oldY = y;
			}
			g2d.drawLine((int)((oldX * 40 + 960 + xOffset) * graphics.scale), (int)((1080 - oldY * 40 - 540 + yOffset) * graphics.scale), (int)((x * 40 + 960 + xOffset) * graphics.scale), (int)((1080 - y * 40 - 540 + yOffset) * graphics.scale));
			oldX = x;
			oldY = y;
		}

	}
	
	public void textArea(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(new Color(255, 254, 252));
		g2d.fillRect(0, 0, (int)(400 * graphics.scale), (int)(150 * graphics.scale));
		g2d.setColor(new Color(0, 0, 0));
		g2d.drawRect(0, 0, (int)(400 * graphics.scale), (int)(150 * graphics.scale));
		
		g2d.setFont(new Font("Arial", Font.PLAIN, (int)(20 * graphics.scale)));
		g2d.drawString("y = " + function, (int)(15 * graphics.scale), (int)(75 * graphics.scale));
		

		
		int blinkRate = 15;
		if(++cursorBlink < blinkRate) g2d.setColor(new Color(0, 0, 0));
		else if(cursorBlink > blinkRate && cursorBlink < 2 * blinkRate) g2d.setColor(new Color(255, 254, 252));
		else if(cursorBlink > 2 * blinkRate) cursorBlink = 0;
		
		int cursorPos = g2d.getFontMetrics().stringWidth("y = " + function) + 17;
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine((int)(cursorPos * graphics.scale), (int)(55 * graphics.scale), (int)(cursorPos * graphics.scale), (int)(80 * graphics.scale));
		
	}
	
	@Override
	public void tick() {
		if(MathEngine.mouseDown) {
			xOffset += MathEngine.dragEventX * .5;
			yOffset += MathEngine.dragEventY * .5;
			
		}
			
	}
	
	public void stop() {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() != '') function += e.getKeyChar();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) function = function.substring(0, function.length() - 1);

	}
	
}