package com.wangxuegang.log4j;

import org.apache.log4j.Logger;

public class Log4jTest {
	
	private static Logger log = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		
		log.debug("11111111111111");
		log.info("22222222222222");
		log.error("11111111111111");
		
	}

}
