package com.wangxuegang.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.junit.Test;

/**
 * 
 * @类描述：测试类
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.serialize
 * @类名称：SerializeTest
 * @创建人：wangxuegang
 * @创建时间：2019年1月16日下午5:04:57
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class SerializeTest {
	
	/**
	 * 
	 * @描述: 序列化
	 * @方法名: serialTest
	 * @返回类型 void
	 * @创建人 wangxuegang
	 * @创建时间 2019年1月16日下午5:06:20
	 * @since
	 * @throws
	 * @throws Exception 
	 */
	@Test
	public void derialTest() throws Exception{
		
		WUser w = new WUser();
		w.setCreateDate(new Date());
		w.setEmail("15510235102@163.com");
		w.setLoginName("wangxuegang");
		w.setPassword("******");
		w.setPhone("13761095490");
		w.setUserId(1);
		w.setUserName("wangxuegang");
		
		System.out.println("w Serial" + w);
        FileOutputStream fos = new FileOutputStream("WUser.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(w);
        oos.flush();
        oos.close();
	}
	
	/**
	 * 
	 * @描述: 反序列化
	 * @方法名: deserialTest
	 * @返回类型 void
	 * @创建人 wangxuegang
	 * @创建时间 2019年1月16日下午5:07:07
	 * @since
	 * @throws
	 */
	@Test
	public void deserialTest() throws Exception{
		
		FileInputStream fis = new FileInputStream("WUser.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		WUser w = (WUser) ois.readObject();
        ois.close();
        System.out.println("w Deserial" + w);
	}
}
