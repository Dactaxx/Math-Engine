package mathengine;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.util.ArrayList;

import graphics.DGraphics;
import graphics.GraphicsInterface;
import graphics.GraphicsThread;
import utilities.GraphingCalculator;

public class MathEngine {
	public static boolean mouseDown = false;
	public static int mouseX, mouseY, oldMouseX, oldMouseY, dragEventX, dragEventY = 0;
	public static DGraphics graphics;
	public static ArrayList<GraphicsInterface> gis = new ArrayList<GraphicsInterface>();
	@SuppressWarnings({ "unused"})
	public static void main(String args[]) {
		//main loop
		GraphicsThread gt = new GraphicsThread() {

			@Override
			public void render(Graphics2D g2d) {
				g2d.drawLine(0,  0, 10,  10);
				
			}

			@Override
			public void tick() {
				oldMouseX = mouseX;
				oldMouseY = mouseY;
				mouseX = MouseInfo.getPointerInfo().getLocation().x;
				mouseY = MouseInfo.getPointerInfo().getLocation().y;
				if(!mouseDown) {
					dragEventX = 0;
					dragEventY = 0;
				}
				if(mouseX - oldMouseX == 0) dragEventX = 0;
				if(mouseY - oldMouseY == 0) dragEventY = 0;
				
			}
			
		};
		gt.setRunState(GraphicsThread.RUNNING);
		gis.add(gt);
		
		graphics = new DGraphics(gis);
		graphics.frame.addMouseListener(new MouseInput());
		graphics.frame.addMouseMotionListener(new MouseInput());
		GraphingCalculator calc = new GraphingCalculator();
		
	}
}
