package com.wangxuegang.thread;


import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @类描述：模拟Queue(队列)
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.thread
 * @类名称：QueueDemo
 * @创建人：wangxuegang
 * @创建时间：2018年12月21日下午4:58:35
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class QueueDemo {
	
	//创建一个链表集合
	private final LinkedList<Object> list = new LinkedList<Object>();
	
	//创建计数器
	private final AtomicInteger count = new AtomicInteger(0);
	
	//最大值
	private final int maxSize;
	
	//最小值
	private final int minSize = 0;
	
	//创建一个锁对象
	private final Object lock = new Object();
	
	//构造方法
	public QueueDemo (int maxSize){
		this.maxSize = maxSize;
	}
	
	//在链表集合中添加元素
	public void put (Object obj) {
		synchronized(lock){
			
			//当前count为0，当count==maxSize时，阻塞线程t1
			while(count.get() == maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			list.add(obj);
			
			//计数器累加方法
			count.getAndIncrement();
			System.out.println(Thread.currentThread().getName()+" 元素 " + obj + " 被添加 ");
			
			//唤醒线程t1
			lock.notify();
			
		}
	}
	
	public Object take(){
		Object temp = null;
		synchronized (lock) {
			
			//当前count为0，当count==minSize时，阻塞线程t2
			while(count.get() == minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//计数器累减方法
			count.getAndDecrement();
			
			//移除链表集合第一元素
			temp = list.removeFirst();
			System.out.println(Thread.currentThread().getName()+" 元素 " + temp + " 被消费 ");
			
			//唤醒线程t2
			lock.notify();
		}
		return temp;
	}
	
	public int size(){
		return count.get();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		final QueueDemo m = new QueueDemo(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("当前元素个数：" + m.size());
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				m.put("h");
				m.put("i");
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
					Object t1 = m.take();
					//System.out.println("被取走的元素为：" + t1);
					Thread.sleep(1000);
					Object t2 = m.take();
					//System.out.println("被取走的元素为：" + t2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t1.start();
		Thread.sleep(1000);
		t2.start();
		
	}
	
}
