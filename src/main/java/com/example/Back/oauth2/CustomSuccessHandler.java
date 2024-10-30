package com.example.Back.oauth2;

import com.example.Back.dto.CustomOAuthUser;
import com.example.Back.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    //로그인 성공시 동작
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuthUser customOAuthUser = (CustomOAuthUser) authentication.getPrincipal();

        String username = customOAuthUser.getUsername();


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        //토큰 작성
        String token = jwtUtil.createJwt(username, role, 60*60*60L);

        response.addCookie(createCookie("Authorization", token));
        //프론트에 리다이렉트 요청
        response.sendRedirect("http://localhost:3000/");


    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
