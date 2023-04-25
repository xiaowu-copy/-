package org.spring.controller;

import org.spring.domain.Swiper_Case;
import org.spring.service.NewsService;
import org.spring.service.Swiper_CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Cases")
public class SwiperCaseController {

    @Autowired
    private Swiper_CaseService swiper_caseService;
    @RequestMapping("/getAll")
    public List<Swiper_Case> getAllCase(){
        return swiper_caseService.getAll();
    }

    @RequestMapping("/getOne/{id}")
    public Swiper_Case getOneNews(@PathVariable(value = "id") int id){
        return swiper_caseService.getOne(id);
    }
}
