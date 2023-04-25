package org.spring.service.impl;

import org.spring.domain.Swiper;
import org.spring.mapper.SwiperMapper;
import org.spring.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperMapper swiperMapper;
    @Override
    public List<Swiper> getCasesAll() {
        return swiperMapper.getCasesAll();
    }

    @Override
    public Swiper getSwiperOne(int id) {
        return swiperMapper.getSwiperOne(id);
    }

    @Override
    public int AddView(int id) {
        return swiperMapper.AddView(id);
    }
}
