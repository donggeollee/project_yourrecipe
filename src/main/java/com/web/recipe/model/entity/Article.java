package com.web.recipe.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@ToString(exclude = {"user","commentList","recommendationList","imageList"})
@EntityListeners(AuditingEntityListener.class)
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String content;
	
	private String thumbnailName;
	
	private	Long readCount; 
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	@ManyToOne
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "article")
	private List<Comment> commentList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "article") 
	private List<Recommendation> recommendationList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "article")
	private List<Image> imageList;
	
	public int getCommentListSize() {
		return commentList.size(); 
	}
	
	public int getRecommendationListSize() {
		return recommendationList.size();
	}
	
	public int getImageListSize() {
		return imageList.size();
	}
	
	public String getStrCreatedAt() { 
		return String.format("%d년  %d월%d일 %d:%d분", updatedAt.getYear(),
				updatedAt.getMonthValue(),updatedAt.getDayOfMonth(),
				updatedAt.getHour(),updatedAt.getMinute());
	} 

}
