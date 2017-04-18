package com.lava.lavafaq.service.impl;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lava.lavafaq.bean.Person;
import com.lava.lavafaq.canstants.Canstants;
import com.lava.lavafaq.dao.PersonMapper;
import com.lava.lavafaq.servic.IPersonService;
import com.lava.lavafaq.utils.MailUtils;


@Service("personService")
public class PersonServiceImpl implements IPersonService {

@Resource	
private PersonMapper personMapper;

private static Logger logger = Logger.getLogger(PersonServiceImpl.class); 


    //注册业务
	public String registerPerson(Person person) {
 	
		if(null ==person.getUsername() || null ==person.getPassword() || null ==person.getMail()){		 
			 return Canstants.regFail;
		 }	
		if(null != personMapper.selectByUsername(person.getUsername())){
			return Canstants.regNameFail;
		}
		if(null != personMapper.selectByMail(person.getMail())){
			return Canstants.regEmailFail;
		}
					
			try {
			person=	MailUtils.sendMail(person);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//插入数据
			if(this.personMapper.insert(person) == -1){
				return Canstants.regFail;
			}else{
				return Canstants.regSuccess;
			}				
	}

	//登录业务
	public String loginPerson(Person person,HttpSession httpSession) {
		if(null ==person.getMail() || null==person.getPassword()){
			return Canstants.loginEmPawNull;
		}
		Person personEnty = personMapper.selectByPwdAndEmail(person);
		if(null == personEnty){
			return Canstants.loginPawFail;
		}
		if(personEnty.getState() ==0){
			return Canstants.loginStateFail;
		}
		//登录成功存储Session	 		
		httpSession.setAttribute("account", personEnty.getMail()); 
		httpSession.setAttribute("username", personEnty.getUsername());
		return Canstants.loginSuccess;
	}
	
	//激活账号业务
	public boolean activatEmail(Person person) {		
		Person personEnty = personMapper.selectByCodeAndEmail(person);
		if(null !=personEnty && personEnty.getState()==0 ){//如果存在，且未激活，则激活。修改数据库
		   personEnty.setState(1);	   
		   if(-1 != personMapper.updateStateByCode(personEnty)){
			   //修改数据状态成功，可登陆
			   logger.debug("ws--activatEmail----成功---");
			   return true;
		   }else {
			   logger.debug("ws--activatEmail----updateStateByCode失败---");
		   }   
		}		 
		return false;
	}

}
