package com.example.springbootthymeleaftw.service;
import com.example.springbootthymeleaftw.model.entity.FirmEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.FirmRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirmService {

    private final FirmRepository firmRepository;
    public FirmEntity saveFirm(FirmEntity firm){return firmRepository.save(firm);}
}
