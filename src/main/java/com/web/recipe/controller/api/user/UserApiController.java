package com.web.recipe.controller.api.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.recipe.controller.CrudController;
import com.web.recipe.model.entity.User;
import com.web.recipe.model.network.request.UserApiRequest;
import com.web.recipe.model.network.response.UserApiResponse;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest,UserApiResponse, User>{
	  
}
 