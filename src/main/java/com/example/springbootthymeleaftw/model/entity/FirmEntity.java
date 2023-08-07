package com.example.springbootthymeleaftw.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

import lombok.Data;


@Entity
@Data
@Table(name = "Firm", schema = "public", catalog = "college")
public class FirmEntity {

    @Id
    @Column(name = "idFirm",nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFirm;

    @Basic
    @Column(name = "firmName",nullable = false)
    private String firmName;
    @Basic
    @Column(name = "address",nullable = false)
    private String address;

    @Basic
    @Column(name = "cif",nullable = false)
    private String cif;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "firm_product",
            joinColumns = @JoinColumn(
                    name = "idFirm", referencedColumnName = "idFirm"),
            inverseJoinColumns = @JoinColumn(
                    name = "idProduct", referencedColumnName = "idProduct"))
    private Collection<ProductEntity> firmProduct;

    @OneToOne(mappedBy = "userFirm", fetch = FetchType.EAGER)
    private UserEntity firmUser;

}