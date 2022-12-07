package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 인증되지 않은 사용자들이 출입할 수 있는 경로 /auth/** 허용
// 기본 주소 / > index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {
	
	@Value("${kjh.key}")
	private String kjhKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수
		
		// POST 방식으로 key=value 데이터를 요청 (카카오측으로)
		RestTemplate rt = new RestTemplate();
		
		// HttpHeaders 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "21c210170351e3337259fac386ccc174");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		// HttpHeaders와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers); 
		
		// Http 요청하기 - Post 방식
		ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class
				);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//System.out.println("카카오 엑세스 토큰 : "+oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
				new HttpEntity<>(headers2); 

		ResponseEntity<String> response2 = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest,
					String.class
				);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 아이디(번호) :"+kakaoProfile.getId());
		System.out.println("카카오 이메일 :"+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그서버 유저네임 : " +kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		System.out.println("블로그서버 패스워드 : "+kjhKey);
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(kjhKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		// 가입자 비가입자 체크
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) {
			System.out.println("기존회원이 아니므로 자동 회원가입 진행");
			userService.회원가입(kakaoUser);
		}
		
		// 로그인 처리

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kjhKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return "redirect:/";
	}
}
