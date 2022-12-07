package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 html 태그가 섞여 디자인이 됨
	
	private int count; // 조회수
	
	@ManyToOne(fetch=FetchType.EAGER) // Many = Board, User = One 1명의 유저는 여러개의 게시글 가능
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedby 연관관계의 주인이 아님. 컬럼 생성 x
	@JsonIgnoreProperties({"board"}) // 무한참조 방지
	@OrderBy("id desc")
	private List<Reply> replies;
	
	@CreationTimestamp
	private Timestamp createDate;
}
