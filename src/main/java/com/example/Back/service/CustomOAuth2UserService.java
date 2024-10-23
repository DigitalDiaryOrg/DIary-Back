package com.example.Back.service;

import com.example.Back.dto.CustomOAuthUser;
import com.example.Back.dto.UserDTO;
import com.example.Back.dto.oAuth.GoogleResponse;
import com.example.Back.dto.oAuth.OAuth2Response;
import com.example.Back.entity.Member;
import com.example.Back.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("google")) {
              oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else{
            return null;
        }

        UserDTO userDTO = new UserDTO();

        //로그인한 유저 기존 로그인 이력 검색
        Optional<Member> member = memberRepository.findMemberByEmailAndProvider(oAuth2Response.getEmail(), oAuth2Response.getProvider());
        if(member.isPresent()){
            Member existMember = member.get();

            existMember.update(oAuth2Response.getEmail());
            memberRepository.save(existMember);

            String username = oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();

            userDTO.setUsername(username);
            userDTO.setName(existMember.getNickname());
            userDTO.setRole(existMember.getRole());

            return new CustomOAuthUser(userDTO);
        }
        else{
            Member newMember = Member.builder()
                    .email(oAuth2Response.getEmail())
                    .role("ROLE_USER")
                    .nickname(oAuth2Response.getEmail())
                    .provider(registrationId)
                    .build();

            memberRepository.save(newMember);

            String username = oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2User.getName());
            userDTO.setRole("ROLE_USER");

        }
        return new CustomOAuthUser(userDTO);

    }
}
