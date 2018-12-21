package com.wangxuegang.pachong;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 
&nbsp;* @类描述：httpclient 4.3以上版本
&nbsp;* @项目名称：com.wangxuegang.pachong
&nbsp;* @包名： com.wangxuegang.pachong
&nbsp;* @类名称：Pachong20181220
&nbsp;* @创建人：wangxuegang
&nbsp;* @创建时间：2018年12月21日上午9:49:30
&nbsp;* @mail 15510235102@163.com
&nbsp;* @version v1.0
&nbsp;
 */
public class Pachong20181220 {
	
	private static Logger log = Logger.getLogger(Pachong20181220.class);
	
	private static int CONNECT_TIME_OUT = 1000;
	private static int SOCKET_TIME_OUT = 2000;
	private static int CONNECTION_REQUEST_TIME_OUT = 2000;
	
	@Test
	public void test01(){
		
		try {
			/** 创建 httpClient 实例 */
			HttpClient httpClient = HttpClients.createDefault();
			
			/** 创建 get()方法 */
			HttpGet httpGet = new HttpGet("https://www.zhihu.com");
			
			/** 超时设置 */
			RequestConfig requestConfig = RequestConfig.custom()
					/** 设置连接超时时间，单位毫秒 */
					.setConnectTimeout(CONNECT_TIME_OUT)
					/** 请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用 */
					.setSocketTimeout(SOCKET_TIME_OUT)
					/** 设置从connect Manager(连接池)获取Connection 超时时间，单位毫秒,这个属性是新加的属性，因为目前版本是可以共享连接池的 */
					.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT)
					.build();
			httpGet.setConfig(requestConfig);
			
			/** get请求，返回响应 */
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			/** 状态 */
			int state = httpResponse.getStatusLine().getStatusCode();
			
			/** 打印 */
			System.out.println("成功："+state);
			
		} catch (ClientProtocolException e) {
			log.error(e.getStackTrace());
		} catch (ConnectTimeoutException e) {
			log.error("温馨提示：连接超时");
		} catch (SocketTimeoutException e) {
			log.error("温馨提示：响应超时");
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
	}
	
	@Test
	public void test02(){
		
		try {
			/** 超时设置 */
			RequestConfig requestConfig = RequestConfig.custom()
					/** 设置连接超时时间，单位毫秒 */
					.setConnectTimeout(CONNECT_TIME_OUT)
					/** 请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用 */
					.setSocketTimeout(SOCKET_TIME_OUT)
					/** 设置从connect Manager(连接池)获取Connection 超时时间，单位毫秒,这个属性是新加的属性，因为目前版本是可以共享连接池的 */
					.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT)
					.build();
			
			/** 创建 httpClient 实例 */
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			/** 连接池个数 */
			httpClientBuilder.setMaxConnTotal(30);
			/** 路由并发数 */
			httpClientBuilder.setMaxConnPerRoute(2);
			/** 使用配置的连接池和路由 */
			httpClientBuilder.setDefaultRequestConfig(requestConfig);
			
			/** 构建httpclient对象*/
			HttpClient httpClient = httpClientBuilder.build();
			
			/** 创建 get()方法 */
			HttpGet httpGet = new HttpGet("https://www.zhihu.com");
			
			/** get请求，返回响应 */
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			/** 状态 */
			int state = httpResponse.getStatusLine().getStatusCode();
			
			/** 打印 */
			System.out.println("成功："+state);
			
		} catch (ClientProtocolException e) {
			log.error(e.getStackTrace());
		} catch (ConnectTimeoutException e) {
			log.error("温馨提示：连接超时");
		} catch (SocketTimeoutException e) {
			log.error("温馨提示：响应超时");
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
	}
}
