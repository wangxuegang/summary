package com.wangxuegang.thread;

/**
 * 
 * @类描述：死锁
 * @项目名称：JavaTest
 * @包名： com.wangxuegang.practice
 * @类名称：DeadLockDemo
 * @创建人：wangxuegang
 * @创建时间：2018年12月19日下午2:19:49
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class DeadLockDemo {
	
	private static String resource_a = "A";
    private static String resource_b = "B";
	
	public static void main(String[] args) {
		deadLock();
	}
	
	public static void deadLock() {
		
		Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (resource_a) {
                    System.out.println("threadA: get resource a");
                    try {
                        Thread.sleep(3000);
                        synchronized (resource_b) {
                            System.out.println("threadA: get resource b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
		
		Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (resource_b) {
                    System.out.println("threadB: get resource b");
                    synchronized (resource_a) {
                        System.out.println("threadB: get resource a");
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
	}
}
