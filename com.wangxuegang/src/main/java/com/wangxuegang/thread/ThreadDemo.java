package com.wangxuegang.thread;

/**
 *  @author wangxuegang
 *  1.synchronizecd使用
 *  2.static和synchronizecd同时使用
 *  3.wait()使用
 *  4.notify()使用
 *  5.clone()使用	
 */
public class ThreadDemo implements Cloneable{
	
	/*MyThread m ;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		m = (MyThread)super.clone();
		return m;
	}*/
	
	
	/********************************************************************
	 * 1.static和synchronized使用
	 * 逻辑:
	 * 		 设计4个线程 ，2个线程同时对变量j进行减减操作，另外2个线程对变量j进行加加操作。
	 * 总结:
	 * 		static和synchronized同时修饰一个变量，表示类锁。
	 * 		synchronizecd 表示对象锁
	 
	private static int j;
	
	public static synchronized void test1(){
		j--;
		System.out.println(Thread.currentThread().getName()+"第一次减减:"+j);
	}
	
	public static synchronized void test2(){
		j++;
		System.out.println(Thread.currentThread().getName()+"第一次加加:"+j);
	}
	
	public static void main(String[] args) {
		
		MyThread mt = new MyThread();
		
		mt.sum();
	
	}

	private void sum() {
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 20; j++) {
						test1();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 20; j++) {
						test2();
					}
				}
			}).start();
		}	
	}
	
	*/
	
	/********************************************************************
	 * 1.static和synchronized使用
	 * 逻辑:
	 * 		 设计4个线程 ，2个线程同时对变量j进行减减操作，另外2个线程对变量j进行加加操作。
	 * 总结:
	 * 		static和synchronized同时修饰一个变量，表示类锁。
	 * 		synchronizecd 表示对象锁
	 * 
	 */
	
	private static int count = 10;
	
	public static void main(String[] args) {
		
    //计数器，具有原子性，在多线程中使用
		/*AtomicInteger count = new AtomicInteger(5);
		
		System.out.println(count.getClass());*/
		
		/*try {
			MyThread m = new MyThread();
			MyThread t = (MyThread) m.clone();
			System.out.println(t.equals(m));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(count.getAndIncrement());
		//System.out.println(count.getAndIncrement());
		
		/**
		 * 逻辑思路:
		 * 	两个线程对count进行减减，在t1线程中，当count等
		 * 	于5时，线程阻塞wait(),t2线程睡眠5秒，然后唤醒t1线程，
		 */
     
		final Object lock = new Object();
		new Thread(new Runnable() {
			public void run() {
				synchronized (lock) {
					for (int i = 1000; i > 0; i--) {
						count--;
						System.out.println(Thread.currentThread().getName()+"==="+count);
						if(count == 5){
							try {
								System.err.println(Thread.currentThread().getName()+"等待。。。。。。。。。。。。。。。。");
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		},"t1").start();
		new Thread(new Runnable() {
			public void run() {
				synchronized (lock) {
					System.out.println(Thread.currentThread().getName()+"==="+count);
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock.notify();
					System.err.println(Thread.currentThread().getName()+"唤醒。。。。。。");
				}
			}
		},"t2").start();
	}
}
