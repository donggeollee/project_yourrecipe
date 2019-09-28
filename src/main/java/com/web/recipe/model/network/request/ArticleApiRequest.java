package com.web.recipe.model.network.request;

import java.time.LocalDateTime;
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
public class ArticleApiRequest {

	private Long id;
	
	private String title;
	
	private String content;
	
	private String thumbnailName; 
	
	private	Long readCount;
	
	private Long userId;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
}
