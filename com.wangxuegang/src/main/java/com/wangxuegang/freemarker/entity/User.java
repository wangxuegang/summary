package com.wangxuegang.freemarker.entity;

import java.io.Serializable;

/**
 * 
 * @类描述：User实体类
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.freemarker.entity
 * @类名称：User
 * @创建人：wangxuegang
 * @创建时间：2019年2月15日下午3:12:41
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class User implements Serializable {
	
    
	/**
	 * @字段：serialVersionUID
	 * @功能描述：
	 * @创建人：wangxuegang
	 * @创建时间：2019年2月15日下午3:12:27
	 */
	private static final long serialVersionUID = -2807089691552371712L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}