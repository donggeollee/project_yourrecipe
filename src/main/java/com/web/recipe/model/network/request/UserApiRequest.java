package com.web.recipe.model.network.request;

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
public class UserApiRequest {

	private Long id;
	
	private String account;
	
	private String password;
	
	private String nickname;
	
	private String gender;
}
