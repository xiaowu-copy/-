package org.spring.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.Swiper;
import org.spring.domain.Swiper_Case;
import org.spring.domain.Video;

import java.util.List;
@Mapper
public interface SwiperMapper {
    public List<Swiper> getCasesAll();

    public Swiper getSwiperOne(@Param("id") int id);

    public int AddView(@Param("id") int id);
}
