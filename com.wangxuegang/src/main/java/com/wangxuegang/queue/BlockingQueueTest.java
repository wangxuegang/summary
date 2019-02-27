package com.wangxuegang.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<String>(4);
		arrayQueue.add("wangxuegang1");
		arrayQueue.add("wangxuegang2");
		arrayQueue.add("wangxuegang3");
		arrayQueue.add("wangxuegang4");
		
		System.out.println(arrayQueue.take());
		System.out.println(arrayQueue.poll());
		System.out.println(arrayQueue.size());
		
		for (String string : arrayQueue) {
			System.out.println(string);
		}
		
		BlockingQueue<String> synQueue = new SynchronousQueue<String>();
		synQueue.take();
		synQueue.add("wangxuegang01");
		//synQueue.add("wangxuegang02");
		//System.out.println(synQueue.size());
		System.out.println(synQueue.take());
	}

}
