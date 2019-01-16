package com.wangxuegang.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 
 * @description 照片压缩工具类
 * @author wangxuegang
 * @since JDK 1.6
 */
public class ImageZipTest {
	
	/**
	 * 照片大小
	 */
	private static final Integer PHOTO_SIZE = 102400;
	
	/**
	 * 
	 * @description 照片
	 * @param bt 原始照片字节
	 * @param scale 缩放比例
	 * @param flag 缩放选择:true 放大; false 缩小
	 * @throws IOException 
	 * @since JDK 1.6
	 */
	public static void pic(byte[] bt,Integer scale,boolean flag) throws IOException{
		
		System.out.println("缩放比例："+scale);
		
		InputStream is = new ByteArrayInputStream(bt);
		
		//原始照片
		BufferedImage oldImage = ImageIO.read(is);
		
		//照片压缩
		BufferedImage newImage = picCompress(oldImage, scale, flag);
		
		//字节数组输出流
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		
		//图片输出流
		ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
		ImageIO.write(newImage, "jpg", imOut);
		
		//字节输入流
		InputStream contentStream = new ByteArrayInputStream(bs.toByteArray());
		System.out.println("压缩后大小："+contentStream.available());
		System.out.println("条件大小：："+PHOTO_SIZE);
		
		//判断照片压缩后的大小
		while(contentStream.available() > PHOTO_SIZE){
			scale +=1;
			//不满足照片大小，递归继续压缩照片
			pic(bs.toByteArray(), scale, flag);
			ImageIO.write(newImage, "JPEG", new File("C:\\Users\\wangxuegang\\Desktop\\萌_01.jpg"));
			
			contentStream.close();
			bs.close();
			is.close();
			break;
		}
	}
	
	/**
	 * 
	 * @description 照片压缩
	 * @param image 原始照片
	 * @param scale 缩放比例
	 * @param flag 缩放选择:true 放大; false 缩小
	 * @since JDK 1.6
	 */
	private static BufferedImage picCompress(BufferedImage image,Integer scale,boolean flag) {
		//原始照片宽高
		int width = image.getWidth();
		int height = image.getHeight();
		if(flag) {// 放大
            width = width * scale;
            height = height * scale;
        } else {// 缩小
            width = width / scale;
            height = height / scale;
        }
		System.out.println("width："+width);
		System.out.println("height："+height);
		Image img = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = outputImage.getGraphics();
		graphics.drawImage(img, 0, 0, null);
		graphics.dispose();
		return outputImage;
	}
	
	/**
	 * 
	 * @description 测试Main
	 * @since JDK 1.6
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		InputStream is = new FileInputStream(new File("C:\\萌.jpg"));
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] bt = new byte[1024];
		int len = 0;
		while((len = is.read(bt))>0){
			bo.write(bt,0,len);
		}
		
		bo.close();
		is.close();
		
		pic(bo.toByteArray(),5,false);
		
	}
}