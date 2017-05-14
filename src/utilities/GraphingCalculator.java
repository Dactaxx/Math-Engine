package utilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import graphics.GraphicsThread;
import mathengine.MathEngine;

public class GraphingCalculator extends GraphicsThread {
	private double xOffset = 0;
	private double yOffset = 0;
	
	public GraphingCalculator()	{
		MathEngine.gis.add(this);
		this.setRunState(GraphicsThread.RUNNING);
		MathEngine.graphics.update(MathEngine.gis);
		
	}
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillRect(0, 0, 1920, 1080);

		g2d.setColor(new Color(200, 200, 200));
		drawGrid(g2d);
		
		g2d.setColor(new Color(0, 0, 0));
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine((int)(960 + xOffset),  0,  (int)(960 + xOffset),1080);
		g2d.drawLine(0, (int)(540 + yOffset), 1920, (int)(540 + yOffset));
		
		g2d.setStroke(new BasicStroke(5));
		graph(g2d);
		
		textArea(g2d);
		
	}

	private void drawGrid(Graphics2D g2d) {
		for(int i = 20; i < 1080; i += 40) {
			g2d.drawLine((int)(0), i + yOffset >= 0 ? Math.abs((int)(i + yOffset) % 1080) : 1080 - Math.abs((int)(i + yOffset) % 1080), (int)(1920), i + yOffset >= 0 ? Math.abs((int)(i + yOffset) % 1080) : 1080 - Math.abs((int)(i + yOffset) % 1080));
		}
		
		for(int i = 0; i < 1920; i += 40) {
			g2d.drawLine(i + xOffset >= 0 ? Math.abs((int)(i + xOffset) % 1920) : 1920 - Math.abs((int)(i + xOffset) % 1920), (int)(0), i + xOffset >= 0 ? Math.abs((int)(i + xOffset) % 1920) : 1920 - Math.abs((int)(i + xOffset) % 1920), (int)(1080));
		}
	}
	
	private void graph(Graphics2D g2d) {
		double oldX  = .112358, oldY = .112358;
		g2d.setColor(new Color(0, 0, 255));
		for(double x = -xOffset / 40 - 24; x < -xOffset / 40 + 24; x += .02) {
			double y = (x - 3) * (x - 2) * (x - 1) * x * (x +1) * (x + 2) * (x + 3) * .1;
			if(oldX == .112358) {
				oldX = x;
				oldY = y;
			}
			g2d.drawLine((int)(oldX * 40 + 960 + xOffset), (int)(1080 - oldY * 40 - 540 + yOffset), (int)(x * 40 + 960 + xOffset), (int)(1080 - y * 40 - 540 + yOffset));
			oldX = x;
			oldY = y;
		}

	}
	
	public void textArea(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(new Color(255, 254, 252));
		g2d.fillRect(0, 0, 400, 150);
		g2d.setColor(new Color(0, 0, 0));
		g2d.drawRect(0, 0, 400, 150);
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
}
