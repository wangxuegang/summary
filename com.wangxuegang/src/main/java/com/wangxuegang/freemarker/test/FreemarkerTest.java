package com.wangxuegang.freemarker.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wangxuegang.freemarker.entity.User;
import com.wangxuegang.freemarker.util.FreemarkerUtil;

/**
 * 
 * @类描述：FreemarkerTest测试类
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.freemarker.test
 * @类名称：FreemarkerTest
 * @创建人：wangxuegang
 * @创建时间：2019年2月15日下午3:11:55
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class FreemarkerTest {
	
    @Test
    public void test(){
    	
        FreemarkerUtil util = new FreemarkerUtil();
        Map<String, Object> map = new HashMap<String, Object>();
         
        User user = new User();
        user.setName("张三");
         
        List<User> users = new ArrayList<User>();
        users.add(user);
         
        map.put("user", user);
        
        util.print("01.ftl", map );
    }
}