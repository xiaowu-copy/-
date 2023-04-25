package org.spring.service.impl;

import org.spring.domain.Swiper_Case;
import org.spring.mapper.SwiperMapper;
import org.spring.mapper.Swiper_CaseMapper;
import org.spring.service.Swiper_CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Swiper_CaseSeviceImpl implements Swiper_CaseService {
    @Autowired
    Swiper_CaseMapper swiper_caseMapper;
    @Override
    public List<Swiper_Case> getAll() {

        return swiper_caseMapper.getAll();

    }

    @Override
    public Swiper_Case getOne(int id) {
        return swiper_caseMapper.getOne(id);
    }
}
