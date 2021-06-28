/**
 * Created on 2015. 3. 8.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.cse.grimpan.strategy;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

public class DrawPanelView extends JPanel {

	/**
	 * 
	 */
	
	private GrimPanFrameMain mainFrame = null;
	
	private static final long serialVersionUID = 1L;
	private GrimPanModel model;
	private DrawPanelView thisView = this;
	
	private int baseWidth = 400; // in milli
	private int baseHeight = 300; // in milli
	
	public JPopupMenu jPopupMenuEdit = null;
	private JMenuItem jpmiZoomIn = null;
	private JMenuItem jpmiZoomOut = null;
	private JMenuItem jpmiMove = null;
	private JMenuItem jpmiResize = null;
	private JMenuItem jpmiDelete = null;
	
	public DrawPanelView(GrimPanModel model, GrimPanFrameMain mf, int w, int h){

		
		this.model = model;
		this.mainFrame = mf;
		
		this.baseWidth = w;
		this.baseHeight = h;
		
		DrawPanelMouseAdapter mouseAdapter = new DrawPanelMouseAdapter(model, this);
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (GrimShape grimShape:model.shapeList){
			grimShape.draw(g2);
		}

		if (model.curDrawShape != null){
			g2.setColor(model.getShapeStrokeColor());
			g2.setStroke(new BasicStroke(model.getShapeStrokeWidth()));
			
			g2.draw(model.curDrawShape);

			if (model.isShapeFill() 
					&& model.getEditState()!=model.STATE_PENCIL){
				g2.setColor(model.getShapeFillColor());
				g2.fill(model.curDrawShape);
			}
		}
	}

}
