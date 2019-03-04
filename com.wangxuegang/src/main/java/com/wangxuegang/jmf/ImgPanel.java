package com.wangxuegang.jmf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 面板
 * @description ImgPanel
 * @author wangxuegang
 * @since JDK 1.6
 */
public class ImgPanel extends Panel implements MouseMotionListener{

	private static final long serialVersionUID = 6770697105194112743L;
	
	/** 面板 默认初始值 */
	private int rectX;
    private int rectY;
    private int rectWidth = 640;
    private int rectHeight = 480;
	private int imgWidth = 640;
    private int imgHeight = 480;
	private Image image = null;
	
	public void setImage(Image image) {
		this.image = image;
	}

	public ImgPanel() {

		setLayout(null);
		setSize(imgWidth, imgHeight);

	}
	
	@Override
	public void update(Graphics g) {
		
		g.clearRect(0, 0, getWidth(), getHeight());
		if (image != null){
			g.drawImage(image, 0, 0, this);
            g.setColor(Color.white);
            g.drawRect(rectX, rectY, rectWidth, rectHeight);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		
		update(g);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		rectX = e.getX() - 50;
        rectY = e.getY() - 50;
        repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		// TODO Auto-generated method stub
		
	}

}

