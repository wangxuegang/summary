package com.wangxuegang.thread;

/**
 * 
 * @类描述：守护线程
 * @项目名称：JavaTest
 * @包名： com.wangxuegang.practice
 * @类名称：DaemonDemo
 * @创建人：wangxuegang
 * @创建时间：2018年12月19日下午3:20:18
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class DaemonDemo {
	/** 主线程 main 结束，守护线程退出将不再继续执行 finally */
	public static void main(String[] args) {
		Thread daemonThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println("i am alive");
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println("finally block");
					}
				}
			}
		});
		/** 线程可以通过setDaemon(true)的方法将线程设置为守护线程。并且需要注意的是设置守护线程要先于start()方法 */
		daemonThread.setDaemon(true);
		daemonThread.start();
		/** 确保main线程结束前能给daemonThread能够分到时间片 */
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
