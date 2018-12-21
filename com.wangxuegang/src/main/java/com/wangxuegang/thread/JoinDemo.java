package com.wangxuegang.thread;

/**
 * 
 * @类描述：线程协作
 * @项目名称：JavaTest
 * @包名： com.wangxuegang.practice
 * @类名称：JoinDemo
 * @创建人：wangxuegang
 * @创建时间：2018年12月19日下午3:11:33
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class JoinDemo {
	public static void main(String[] args) {
		Thread previousThread = Thread.currentThread();
		for (int i = 1; i <= 10; i++) {
			Thread curThread = new JoinThread(previousThread);
			curThread.start();
			previousThread = curThread;
		}
	}

	static class JoinThread extends Thread {
		private Thread thread;

		public JoinThread(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			try {
				thread.join();
				System.out.println(thread.getName() + " terminated.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
