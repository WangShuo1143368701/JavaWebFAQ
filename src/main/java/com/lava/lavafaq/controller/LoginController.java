package com.lava.lavafaq.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lava.lavafaq.bean.Person;
import com.lava.lavafaq.canstants.Canstants;
import com.lava.lavafaq.servic.IPersonService;
import com.lava.lavafaq.utils.ResponseUtils;



@Controller  
@RequestMapping("/login")  
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class); 
	@Resource
	private IPersonService personService;
	
	//注册请求
	@RequestMapping("/register")
	@ResponseBody
	private Object register(Person person){	   
	   person.setState(0);
	   return ResponseUtils.sendSuccess(this.personService.registerPerson(person));		 
	}
	
	//邮件激活
	@RequestMapping("/activatemail")
	private String activatemail(String actiCode,String email,ModelMap modelMap){
		 
	    Person person = new Person();
		person.setActiCode(actiCode);
		person.setMail(email);	
		boolean isAc = this.personService.activatEmail(person);		
		modelMap.addAttribute("isAc",isAc);
		return "activateCode";				  	 
	}
	
	//登录页面
	   @RequestMapping("/loginHtml")  
       public String loginHtml(){ 
		   
           return "login";   
       }
	
	//登录请求   
	@RequestMapping("/login")	
	private String login(Person person,ModelMap modelMap,HttpSession httpSession){		
		String result = this.personService.loginPerson(person,httpSession);
		if(result.equals(Canstants.loginSuccess)){
			
			 return "faqEdit";	
		}
		 modelMap.addAttribute("result",result);		
	   return "redirect:/login/loginHtml";	 
	}
	

}
