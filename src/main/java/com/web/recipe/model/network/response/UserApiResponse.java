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
public class UserApiResponse {

	private Long id;
	
	private String account;
	
	private String password;
	
	private String nickname;
	
	private String gender;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
}
