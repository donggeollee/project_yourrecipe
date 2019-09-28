package com.web.recipe.model.network.response;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.web.recipe.model.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class CommentApiResponse {

	private Long id;
	
	private String content;

	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
	private Long articleId;
	
	private String nickname;

}
