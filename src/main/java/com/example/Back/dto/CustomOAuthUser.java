package com.example.Back.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuthUser implements OAuth2User {

    private final UserDTO userDTO;

    public CustomOAuthUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    //도메인에 따라 값이 다르게 때문에 획일화 힘듬
    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDTO.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

    public String getUsername(){
        return userDTO.getUsername();
    }
}
