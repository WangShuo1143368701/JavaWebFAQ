package com.lava.lavafaq.servic;

import javax.servlet.http.HttpSession;

import com.lava.lavafaq.bean.Person;


public interface IPersonService {
	public String registerPerson(Person person);
	
	public String loginPerson(Person person,HttpSession httpSession);

	public boolean activatEmail(Person person);

	
}
