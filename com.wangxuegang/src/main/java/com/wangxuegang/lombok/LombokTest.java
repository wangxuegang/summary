package com.wangxuegang.lombok;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 
 * @类描述：lombok 使用说明
 * @项目名称：com.wangxuegang
 * @包名： com.wangxuegang.lombok
 * @类名称：LombokTest
 * @创建人：wangxuegang
 * @创建时间：2018年12月21日下午5:38:33
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class LombokTest {
	
	/** @Setter：set()方法，@Getter：get()方法 */
	@Setter @Getter private String name;
	
	public static void main(String[] args) {
		
		LombokTest lombokTest = new LombokTest();
		lombokTest.setName("123");
		System.out.println(lombokTest.getName());
		
		Person person = new Person(null, null);
		System.out.println(person.toString());
	}
}

/** @Data：该类字段生成set()和get()方法 */
@Data
class Person {
	
	/** @NonNull：生成有参构造方法，值为null，抛NullPointerException异常 */
	@NonNull private String name;
	@NonNull private String sex;
}
