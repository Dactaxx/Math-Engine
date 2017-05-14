package mathengine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	private int oldX, oldY = -69;

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
	public void mousePressed(MouseEvent e) {
		MathEngine.mouseDown = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MathEngine.mouseDown = false;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
			oldX = e.getX();
			oldY = e.getY();

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		MathEngine.dragEventX = e.getX() - oldX;
		MathEngine.dragEventY = e.getY() - oldY;
		oldX = e.getX();
		oldY = e.getY();
	}
}
