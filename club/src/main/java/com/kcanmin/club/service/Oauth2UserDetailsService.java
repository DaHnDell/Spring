package com.kcanmin.club.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.MemberRole;
import com.kcanmin.club.repository.MemberRepository;
import com.kcanmin.club.security.dto.AuthMemberDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Oauth2UserDetailsService extends DefaultOAuth2UserService{
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private MemberRepository repository;


  /**
   * @param OAuth2UserRequest userRequest
   * @return return OAuth2User;
   */
  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info(userRequest);
    String clientName = userRequest.getClientRegistration().getClientName();
    Map<?, ?> params = userRequest.getAdditionalParameters();
    OAuth2User auth2User = super.loadUser(userRequest);

    log.info("클라이언트 이름 : : : " + clientName);
    log.info("파라미터 : : : " + params);

    String authedUserEmail = null;
    auth2User.getAttributes().forEach((k, v) -> {log.info("이것은 만능열쇠 키~ : : : " + k); log.info("밸류 밸류 뉴 밸류 : : : " + v);});
    if(clientName.equalsIgnoreCase("google")){
      log.info(": : : Email : : : =============================");
      authedUserEmail = auth2User.getAttribute("email");
      log.info("===============================================");
    }

    Member member = saveSocialMember(authedUserEmail);
    AuthMemberDTO authMemberDTO = new AuthMemberDTO(member.getEmail(), member.getPassword(), member.getMno(), 
    member.getFromSocial(), member.getName(),member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList(), auth2User.getAttributes());

    return authMemberDTO;
  }

  /**
   * @param email
   * @return member || null
   */
  @Transactional
  private Member saveSocialMember(String email){
    log.info(email);
    Member member = repository.findByEmailAndFromSocial(email, true);
    if(member != null){
      return member;
    }

    Member member2 = Member
      .builder()
      .email(email)
      .password(encoder.encode("1234"))
      .fromSocial(true)
      .build();
    member2.addMemberRole(MemberRole.USER);
    repository.save(member2);

    return member2;
  }
}
