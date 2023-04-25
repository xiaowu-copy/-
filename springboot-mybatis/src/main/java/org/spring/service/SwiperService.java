package org.spring.service;


import org.apache.ibatis.annotations.Param;
import org.spring.domain.Swiper;

import java.util.List;

public interface SwiperService {
    public List<Swiper> getCasesAll () ;

    public Swiper getSwiperOne(@Param("id") int id);

    public int AddView(@Param("id") int id);
}
