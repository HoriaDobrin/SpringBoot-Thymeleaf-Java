package com.example.springbootthymeleaftw.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "Role", schema = "public", catalog = "college")
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRole", nullable = false)
    private Long idRole;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<UserEntity> users;
}
