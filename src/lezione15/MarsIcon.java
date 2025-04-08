package lezione15;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class MarsIcon extends ImageIcon {
	public MarsIcon(int size) {
		BufferedImage bi = new
			    BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double planet = new Ellipse2D.Double(0, 0,
		        size, size);
		g2.setColor(Color.RED);
		g2.fill(planet);
		this.setImage(bi);
	}
	
}
