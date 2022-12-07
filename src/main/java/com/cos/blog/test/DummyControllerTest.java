package com.cos.blog.test;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepoisotry;
	 
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepoisotry.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 존재하지 않습니다.";
		}
		
		
		return "삭제되었습니다. id : " + id;
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id :" + id);
		System.out.println("password :" + requestUser.getPassword());
		System.out.println("email :" + requestUser.getEmail());
		
		User user = userRepoisotry.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		
		//userRepoisotry.save(user);
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepoisotry.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepoisotry.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
	// {id} 주소로 파라미터를 전달 받기
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepoisotry.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 존재하지 않습니다. id : "+id);
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter가 응답시 자동 작동
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepoisotry.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
