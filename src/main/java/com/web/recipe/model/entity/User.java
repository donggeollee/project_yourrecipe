package com.web.recipe.model.entity;

import java.time.LocalDateTime;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@ToString(exclude = {"articleList","recommendationList","commentList"})
@EntityListeners(AuditingEntityListener.class) // -> 오류나면 변경할 클래스 AuditingEntityListener.class  
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String account;
	
	private String password;
	
	private String nickname;
	
	private String gender;
	
	@Transient
	private String rememberAccount; 
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Article> articleList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private List<Recommendation> recommendationList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private List<Comment> commentList;

}
