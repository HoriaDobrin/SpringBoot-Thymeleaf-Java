package com.example.springbootthymeleaftw.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="User", schema = "public", catalog = "college")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Long idUser;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "email", unique = true) /* Duplicates emails not allowed */
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            /* The table app_users_roles does not need representation in code */
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "idUser", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(
                    name = "idRole", referencedColumnName = "idRole"))
    private RoleEntity role;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_firms",
            joinColumns = @JoinColumn(
                    name = "idUser", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(
                    name = "idFirm", referencedColumnName = "idFirm"))
    private FirmEntity userFirm;
}