package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.RoleEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.RoleRepository;
import com.example.springbootthymeleaftw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optUser = userRepository.findByEmail(email);
        if (optUser.isPresent()) {
            UserEntity appUser = optUser.get();
            return new User(
                    appUser.getUsername(), appUser.getPassword(), true, true, true, true,
                    /* User Roles */
                    Objects.isNull(appUser.getRole()) ?
                            new ArrayList(List.of(new SimpleGrantedAuthority("client")))
                            : new ArrayList(List.of(new SimpleGrantedAuthority(appUser.getRole().getName())))
            );
        }

        throw new UsernameNotFoundException(email);
    }


    public void addRole(String email, String roleName)
    {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        RoleEntity role = roleRepository.findByName(roleName);

        user.getRole().setName(roleName);
    }

    public Optional<UserEntity> findByEmailService(String email)
    {
        return userRepository.findByEmail(email);
    }

    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void login(String email, String password){
        UserDetails userDetails = this.loadUserByUsername(email);

        if(Objects.isNull(userDetails))
            return;

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
