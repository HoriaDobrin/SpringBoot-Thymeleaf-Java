package com.example.springbootthymeleaftw.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

import lombok.Data;

@Entity
@Data
@Table(name = "Product", schema = "public", catalog = "college")
public class ProductEntity {
    @Id
    @Column(name = "idProduct",nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Basic
    @Column(name = "productName",nullable = false)
    private String productName;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany(mappedBy = "firmProduct", fetch = FetchType.EAGER)
    private Collection<FirmEntity> productFirm;

}