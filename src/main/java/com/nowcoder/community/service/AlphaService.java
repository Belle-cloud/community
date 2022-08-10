package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("prototype")
public class AlphaService {
    public AlphaService(){
        System.out.println("Entity AlphaService");
    }

    @PostConstruct
    public void init(){
        System.out.println("Init AlphaService");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroy AlphaService");
    }

    @Autowired
    private AlphaDao alphaDao;

    public String find(){
        return alphaDao.select();
    }
}
