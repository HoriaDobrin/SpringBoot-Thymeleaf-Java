package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.FirmEntity;
import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.RoleEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmRepository extends JpaRepository<FirmEntity, Long> {
    FirmEntity findByFirmName(String firmName);
}
