package com.lava.lavafaq.controller;



import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lava.lavafaq.bean.Faq;
import com.lava.lavafaq.servic.IFaqService;
import com.lava.lavafaq.utils.ResponseUtils;

@Controller  
@RequestMapping("/faq")
public class FaqController {
	
	@Autowired
	private IFaqService faqService;
	
	private static Logger logger = Logger.getLogger(FaqController.class); 
	//faq编辑
	@RequestMapping("/faqEdit")
	@ResponseBody
	private Object faqEdit(Faq faq,HttpSession httpSession){
		String account= (String) httpSession.getAttribute("account"); 
		String username= (String) httpSession.getAttribute("username"); 
		if(null == account || null==username){
			return "redirect:/login/loginHtml";	
		}
		faq.setMail(account);
		faq.setUsername(username);
		faq.setDatetime(new Date());
		
		String str=faqService.insertFaq(faq);
		logger.error("ws faqEdit="+faq.toString());
	   return ResponseUtils.sendSuccess(str);	  	 
	}

}
