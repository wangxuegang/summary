package com.wangxuegang.thread;

/**
 * 
 * @类描述：线程中断
 * @项目名称：JavaTest
 * @包名： com.wangxuegang.practice
 * @类名称：InterruptDemo
 * @创建人：wangxuegang
 * @创建时间：2018年12月19日下午2:48:10
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class InterruptDemo {
	
	public static void main(String[] args) {
		//sleepThread睡眠1000ms
	    final Thread sleepThread = new Thread() {
	        @Override
	        public void run() {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            super.run();
	        }
	    };
	    //busyThread一直执行死循环
	    Thread busyThread = new Thread() {
	        @Override
	        public void run() {
	            while (true) ;
	        }
	    };
	    
	    /** 启动线程 */
	    sleepThread.start();
	    busyThread.start();
	    
	    /** 中断线程 */
	    sleepThread.interrupt();
	    busyThread.interrupt();
	    
	    /** 如果sleepThread线程中断 main 线程继续往下执行 */
	    while (sleepThread.isInterrupted()) ;
	    System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
	    System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());
}
}
