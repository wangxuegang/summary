package com.wangxuegang.classloader;

public class TestClassLoader {
	
	/**
	 * 
	 * @描述: Main()方法
	 * @方法名: main
	 * @param args
	 * @throws Exception
	 * @返回类型 void
	 * @创建人 wangxuegang
	 * @创建时间 2019年2月27日下午1:18:08
	 * @since
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		
		/** 类的全限定名 **/
		String name = "com.wangxuegang.classloader.MessageImpl";
		
		Message msg = null;
		while(true){
			MyClassLoader loader = new MyClassLoader(Thread.currentThread().getContextClassLoader(), name);
			Class<?> clazz = loader.loadClass(name);
			msg = (Message) clazz.newInstance();
			msg.send();
			
			Thread.sleep(3000);
		}
	}
}
