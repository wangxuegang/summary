package com.wangxuegang.jmf;

import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;

/**
 * 获取所有可用的多媒体设备
 * @description CaptureDeviceAvailable
 * @author wangxuegang
 * @since JDK 1.6
 */
public class CaptureDeviceAvailable {

	@SuppressWarnings("all")
	public static void main(String[] args) {
		
		/** 获得捕获设备列表 */
		Vector deviceList = CaptureDeviceManager.getDeviceList(null);
		System.out.println("找到多媒体设备:" + deviceList.size());
		for (int i = 0; i < deviceList.size(); i++) {
			System.out.println("多媒体设备" + i + ":\n" + deviceList.get(i));
		}
		
		/** 单独获取摄像头 */
		CaptureDeviceInfo webcam = CaptureDeviceManager
				.getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
		System.out.println("摄像头：\n" + webcam);
	}

}
