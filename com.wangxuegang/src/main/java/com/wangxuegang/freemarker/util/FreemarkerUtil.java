package com.wangxuegang.freemarker.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @类描述：FreemarkerUtil工具类
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.freemarker.util
 * @类名称：FreemarkerUtil
 * @创建人：wangxuegang
 * @创建时间：2019年2月15日下午3:09:39
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class FreemarkerUtil {
	
	/**
	 * 
	 * @描述: 获取Freemarker模版
	 * @方法名: getTemplate
	 * @param name
	 * @return
	 * @返回类型 Template
	 * @创建人 wangxuegang
	 * @创建时间 2019年2月15日下午3:10:21
	 * @since
	 * @throws
	 */
    public Template getTemplate(String name) {
        try {
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration();
            // 设定去哪里读取相应的ftl模板文件
            cfg.setClassForTemplateLoading(this.getClass(), "/com/wangxuegang/freemarker/ftl/");
            // 在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @描述: 控制台输出页面
     * @方法名: print
     * @param name
     * @param root
     * @返回类型 void
     * @创建人 wangxuegang
     * @创建时间 2019年2月15日下午3:11:07
     * @since
     * @throws
     */
    public void print(String name, Map<String, Object> root) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = this.getTemplate(name);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}