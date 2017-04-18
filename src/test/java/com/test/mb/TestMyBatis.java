package com.test.mb;

import javax.annotation.Resource;  

import org.apache.log4j.Logger;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
 
import com.alibaba.fastjson.JSON;  
import com.lava.lavafaq.bean.Person;
import com.lava.lavafaq.servic.IPersonService;

 
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
 
public class TestMyBatis {  
   private static Logger logger = Logger.getLogger(TestMyBatis.class);  
 //private ApplicationContext ac = null;  
   @Resource  
   private IPersonService personService = null;  
 
/* @Before  
 public void before() {  
     ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
     userService = (IUserService) ac.getBean("userService");  
 } */ 
 
   @Test  
   public void test1() { 
	    Person person = new Person();	
		person.setMail("114336");
		person.setPassword("114336");
      
      // logger.info(personService.loginPerson(person));  
       logger.info("wangshuo");
   }  
}  