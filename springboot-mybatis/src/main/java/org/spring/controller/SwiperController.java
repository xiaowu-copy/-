package org.spring.controller;


import org.apache.ibatis.annotations.Param;
import org.spring.domain.Swiper;
import org.spring.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Cases")
public class SwiperController {
    @Autowired
    public SwiperService swiperService;

    @RequestMapping("/GetCasesAll")
    public List<Swiper> getVaseAll(){

        return swiperService.getCasesAll();
    }

    @RequestMapping("/GetSwiperOne")
    public Swiper GetSwiperOne(@Param("id") int id){

        return swiperService.getSwiperOne(id);
    }

    @RequestMapping("/View")
    public int View(@Param("id") int id){
        return swiperService.AddView(id);
    }

}
