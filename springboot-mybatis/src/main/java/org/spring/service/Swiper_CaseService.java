package org.spring.service;

import org.spring.domain.Swiper_Case;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Swiper_CaseService {

    public List<Swiper_Case> getAll();

    public Swiper_Case getOne(int id);
}

