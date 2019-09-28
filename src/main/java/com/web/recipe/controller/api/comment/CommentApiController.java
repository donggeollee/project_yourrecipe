package com.web.recipe.controller.api.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.recipe.controller.CrudController;
import com.web.recipe.model.entity.Comment;
import com.web.recipe.model.network.request.CommentApiRequest;
import com.web.recipe.model.network.response.CommentApiResponse;

@RestController
@RequestMapping("/api/comment")
public class CommentApiController extends CrudController<CommentApiRequest, CommentApiResponse, Comment> {
}
