package org.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.spring.domain.Swiper_Case;

import java.util.List;

@Mapper
public interface Swiper_CaseMapper {
    public List<Swiper_Case> getAll();

    public Swiper_Case getOne(int id);
}
