package com.wangxuegang.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @类描述：自定义类热加载
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.classloader
 * @类名称：MyClassLoader
 * @创建人：wangxuegang
 * @创建时间：2019年2月27日上午11:11:05
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class MyClassLoader extends ClassLoader{
	
	/** class 名称 **/
	private String name;
	
	public MyClassLoader(ClassLoader parent,String name) {
		super(parent);
		this.name = name;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		
		Class<?> clazz = null;
		
		if(this.name.equals(name)){
			clazz = this.findLoadedClass(name);
			if(clazz == null){
				clazz = this.findClass(name);
			}
		}
		
		return super.loadClass(name);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = this.readFileToByteArray(name);
		return this.defineClass(name, b, 0, b.length);
	}
	
	/** 读取class名称，处理为class路径转二进制，path指路径，name指类包名+类名 **/
	private byte[] readFileToByteArray(String name){
		
		ByteArrayOutputStream os = null;
		InputStream is = null;
		try {
			String path = "C:\\Users\\Fairyland\\workspace_git\\com.wangxuegang\\target\\classes\\";
			String filePath = path + name.replaceAll("\\.", "/") + ".class";
			is = new FileInputStream(new File(filePath));
			os = new ByteArrayOutputStream();
			int len = 0;
			while((len = is.read())!=-1){
				os.write(len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return os.toByteArray();
	}
}
