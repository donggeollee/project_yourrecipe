package com.web.recipe.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ToString(exclude = {"article","user"})
@EntityListeners(AuditingEntityListener.class)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;

	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	@ManyToOne
	private Article article;
	
	@ManyToOne
	private User user;
	
	public String getStrCreatedAt() { 
		return String.format("%d/%d %d:%d:%d", 
				updatedAt.getMonthValue(),updatedAt.getDayOfMonth(),
				updatedAt.getHour(),updatedAt.getMinute(),updatedAt.getSecond());
	} 
	
	
}
