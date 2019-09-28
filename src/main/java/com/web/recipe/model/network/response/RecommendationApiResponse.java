package com.web.recipe.model.network.response;

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
public class RecommendationApiResponse {

	private Long id;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
	private Long userId;
	
	private Long articleId;
	
	
}
