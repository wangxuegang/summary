package com.wangxuegang.jmf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import jmapps.util.JMFUtils;

/**
 * 拍照
 * @description Camera
 * @author wangxuegang
 * @since JDK 1.6
 */
public class Camera extends JApplet implements ActionListener {

	private static final long serialVersionUID = -1068996593175904031L;
	
	public static Player player = null;
	/** 拍照 */
	private JButton capture = null;
	/** 上传照片 */
	private JButton save = null;
	/** 驱动信息 */
	CaptureDeviceInfo di = null;
	/** 设备地址 */
	MediaLocator ml = null;

	/** 照片面板 */
	private ImgPanel imgpanel = null;
	
	/** 用户 */
	String username = "wangxuegang";
	/** 上传照片名称 */
	private String fname = null;
	
	private Buffer buf = null;
	private Image img = null;
	private BufferToImage btoi = null;
	

	public void init() {

		setLayout(new BorderLayout());
		
		setSize(700, 500);
		imgpanel = new ImgPanel();
		imgpanel.addMouseMotionListener(imgpanel);
		capture = new JButton("拍照");
		capture.addActionListener(this);
		save = new JButton("上传照片");
		save.addActionListener(this);
		String str2 = "vfw:Microsoft WDM Image Capture (Win32):0";
		/** 从驱动管理器中获取驱动,一般使用MicroOS默认的驱动 */
		di = CaptureDeviceManager.getDevice(str2);
		/** 获得本地媒体源 */
		ml = di.getLocator();
		try {
			/** 通过获取的本地源建立播放者对象 */
			DataSource dataSource;
			dataSource = JMFUtils.createCaptureDataSource(null, null, str2,di.getFormats()[0]);
			/** 如果自行设置格式应当使用DataSource对象构建，其中DataSource对象位置为javax.media.protocol.DataSource */
			player = Manager.createRealizedPlayer(dataSource);
			/** 播放者开始播放 */
			player.start();
			Panel panelx2 = new Panel(new GridLayout(1, 2));
			Component comp;
			if ((comp = player.getVisualComponent()) != null) {
				
				/** 增加播放源在窗口上 */
				panelx2.add(comp);
				comp.setSize(new Dimension(320, 240));
			}
			Panel panelx = new Panel(new GridLayout(1, 5));
			Panel panel1 = new Panel(new BorderLayout());
			panelx.add(new Panel());
			panelx.add(capture);
			panelx.add(new Panel());
			panel1.add(new Label("用户：" + username), BorderLayout.WEST);
			panelx.add(save);
			panelx.add(new Panel());
			panel1.add(panelx2, BorderLayout.NORTH);
			panel1.add(panelx, BorderLayout.SOUTH);
			add(panel1, BorderLayout.CENTER);
			panelx2.add(imgpanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void playerclose() {
		player.close();
		player.deallocate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
       
		System.out.println(e.getActionCommand()+"完成");
		
		JComponent c = (JComponent) e.getSource();
		if (c == capture) { 
			/** 抓取当前frame */
			FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
			buf = fgc.grabFrame();  
			/** 转化流格式 */
			btoi = new BufferToImage((VideoFormat) buf.getFormat());
			/** 显示抓取图片 */
			img = btoi.createImage(buf); 
			/** 设置imgpanel图片属性 */
			imgpanel.setImage(img); 

		} else if (c == save) {
			if (img != null) {
				fname = "test";
				saveJPG(img, "C:\\Users\\wangxuegang\\Pictures\\Camera Roll\\",
						fname + ".jpg");
			}
		}

	}

	public void creatMark(String imagePath, String userName) {
		ImageIcon imgIcon = new ImageIcon(imagePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null) + 30;
		try {
			BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			Graphics2D g = bimage.createGraphics();
			/** 设置填充背景为白色 */
			g.setBackground(Color.DARK_GRAY);
			g.clearRect(0, 0, width, height);
			/** 设置字体颜色 */
			g.setColor(Color.white);
			/** 绘制指定图像的当前可用部分 */
			g.drawImage(theImg, 0, 15, null);
			SimpleDateFormat formatter_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String up_time = formatter_time.format(new java.util.Date());
			/** 使用该图形上下文的当前字体和颜色，绘制由指定的字符串给出的文本 */
			g.drawString(("本电子图片由" + "wangxuegang" + "于:" + up_time + "上传"), 10, 15);
			/** 撤消该图形的上下文并释放它所使用的任何系统资源。在 dispose 方法被调用后， Graphics 对象将不能被使用 */
			g.dispose();
			/** 图片输出*/
			FileOutputStream image_out = new FileOutputStream(imagePath); 
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(image_out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(50f, true);
			encoder.encode(bimage, param);
			image_out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;
		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		
		/** 两个字符表示一个字节，所以字节数组长度是字符串长度除以2 */
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public void saveJPG(Image img, String s, String filename) {

		BufferedImage bi = (BufferedImage) createImage(img.getWidth(null),
				img.getHeight(null));
		File filedir = new File(s);
		if (!filedir.exists()) {
			
			/** 新建目录 */
			filedir.mkdirs();
		}
		System.out.println("width:" + img.getWidth(null) + "\theight:" + img.getHeight(null));
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, null, null);
		
		/** 图像输出,保存部分 */
		File f = new File(s + filename);
		if (f.exists()) {
			f.delete();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(f);
		} catch (java.io.FileNotFoundException io) {
			System.out.println("File Not Found");
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(bi);
			out.close();
			creatMark(s + filename, username);
		} catch (java.io.IOException io) {
			System.out.println("IOException");
		}
	}

	public static void main(String[] args) throws IOException {
		Camera wc = new Camera();
		wc.init();
	}

}