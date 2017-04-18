package com.lava.lavafaq.dao;

import com.lava.lavafaq.bean.Faq;

public interface FaqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Faq record);

    int insertSelective(Faq record);

    Faq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Faq record);

    int updateByPrimaryKey(Faq record);
    
    //ws
    Faq selectByPhenomenon(String phenomenon);
}