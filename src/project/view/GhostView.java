/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: GhostView
 */

package project.view;

import project.common.CommonMazeObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class GhostView implements ComponentView {
    private final CommonMazeObject model;
    private final FieldView parent;

    public GhostView(FieldView parent, CommonMazeObject m) {
        this.model = m;
        this.parent = parent;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle bounds = this.parent.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, diameter, diameter);
        g2.setColor(Color.black);
        g2.fill(ellipse);
    }
}
