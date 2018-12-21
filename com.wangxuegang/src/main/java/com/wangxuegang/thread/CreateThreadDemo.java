package com.wangxuegang.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @类描述：3中创建线程方式
 * @项目名称：JavaTest
 * @包名： com.wangxuegang.practice
 * @类名称：CreateThreadDemo
 * @创建人：wangxuegang
 * @创建时间：2018年12月19日下午2:34:22
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class CreateThreadDemo {

	public static void main(String[] args) {
		//1.继承Thread1
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread类");
                super.run();
            }
        };
        thread1.start();
        //2.实现runable接口
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("实现runable接口");
            }
        });
        thread2.start();
        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            public String call() throws Exception {
                return "实现Callable接口";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
