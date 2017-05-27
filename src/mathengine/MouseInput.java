package mathengine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	private int oldX, oldY = -69;
	private boolean dragEvent;
	private boolean canDragInit = false;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MathEngine.mouseDown = false;
		dragEvent = false;
		canDragInit = true;
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		MathEngine.mouseDown = true;
		canDragInit = true;
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(canDragInit) {
			oldX = e.getX();
			oldY = e.getY();
			canDragInit = false;
			
		}
		
		MathEngine.dragEventX = e.getX() - oldX;
		MathEngine.dragEventY = e.getY() - oldY;
			
	}
}
