package com.wangxuegang.jmf;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.CaptureDeviceInfo;
import javax.media.protocol.DataSource;

import com.wangxuegang.test.FrameUtil;



import jmapps.jmstudio.CaptureDialog;
import jmapps.ui.PlayerFrame;
import jmapps.util.JMFUtils;


/**
 * 实现简单的摄像头实时监控
 * @description Video
 * @author wangxuegang
 * @since JDK 1.6
 */
public class Video extends PlayerFrame {
	
	private static final long serialVersionUID = 1L;
	
	DataSource dataSource;

	public Video() {
		super(null, "摄像头监控程序");
	}

	public void init() {
		
		/** 创建设备选择对话框 */
		CaptureDialog dialogCapture = new CaptureDialog(this, null);
		/** 显示选择对话框 */
		dialogCapture.setVisible(true); 
		/** 如果单击了“取消”按钮，则退出程序 */
		if (dialogCapture.getAction().equals(CaptureDialog.ACTION_CANCEL)) {
			System.exit(0);
		}
		/** 获取视频和音频设备，此处其实可以直接根据字符串进行初始化，但对话框选择更具有通用性 */
		CaptureDeviceInfo videoDevice = dialogCapture.getVideoDevice();
		CaptureDeviceInfo audioDevice = dialogCapture.getAudioDevice();
		
		/** 以视频和音频设备创建数据源 */
		dataSource = JMFUtils.createCaptureDataSource(audioDevice.getName(),
				dialogCapture.getAudioFormat(), videoDevice.getName(),
				dialogCapture.getVideoFormat());
		try {
			/** 连接数据源 */
			dataSource.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** 打开数据源 */
		open(dataSource);
		/** 启动播放 */
		mediaPlayerCurrent.start();
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		Video wd = new Video();
		wd.init();
	}

	/**
	 * 窗体关闭时回收资源
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		/** 关闭当前播放器 */
		mediaPlayerCurrent.close();
		/** 关闭数据源 */
		dataSource.disconnect();
		System.exit(0);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/** 组件重绘时窗体居中 */
		FrameUtil.center(this);
	}
}
