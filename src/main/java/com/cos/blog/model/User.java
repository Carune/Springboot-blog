package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // insert 시 null 인 필드 제외
@Entity // User 클래스가 MySQL에 테이블 생성
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password; // 패스워드
	
	@Column(nullable = false, length = 50)
	private String email; // 이메일
	
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum 타입 (ADMIN, USER)
	
	private String oauth; // kakao, google 구분
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
}
