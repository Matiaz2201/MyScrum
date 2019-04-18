package com.myscrum.model;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
public class JGradientPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color finalColor;
    private Color initialColor;
    public JGradientPanel(Color initialColor, Color finalColor) {
        if (initialColor == null)
            throw new IllegalArgumentException("Invalid initial color!");
        if (finalColor == null)
            throw new IllegalArgumentException("Invalid final color!");
        this.initialColor = initialColor;
        this.finalColor = finalColor;
    }
public void setInitialColor(Color color) {
        this.initialColor = color;
        invalidate();
}
public void setFinalColor(Color color) {
    this.finalColor = color;
    invalidate();
}

public Color getInitialColor() {
    return initialColor;        
}

public Color getFinalColor() {
    return finalColor;
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    if (!isOpaque()) {
        return;
    }
    GradientPaint paint = new GradientPaint(
            new Point2D.Float(getWidth() / 2, 0), initialColor, 
            new Point2D.Float(getWidth() / 2, getHeight()), finalColor);
    g2d.setPaint(paint);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    g2d.dispose();
}
}