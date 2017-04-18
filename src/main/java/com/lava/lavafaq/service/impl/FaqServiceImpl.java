package com.lava.lavafaq.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lava.lavafaq.bean.Faq;
import com.lava.lavafaq.canstants.Canstants;
import com.lava.lavafaq.dao.FaqMapper;
import com.lava.lavafaq.servic.IFaqService;

@Service("faqService")
public class FaqServiceImpl implements IFaqService {
	
@Autowired
 private FaqMapper faqMapper;
 
	@Override
	public String insertFaq(Faq faq) {
		// TODO Auto-generated method stub
		if(null !=faqMapper.selectByPhenomenon(faq.getPhenomenon())){
			return Canstants.faqExist;
		}
		int i=faqMapper.insertSelective(faq);
		if(i != -1){
			return Canstants.faqEditSuccess;
		}
		return Canstants.faqEditFail;
	}

}
