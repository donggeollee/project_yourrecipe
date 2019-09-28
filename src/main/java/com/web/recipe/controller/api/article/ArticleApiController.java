package com.web.recipe.controller.api.article;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.recipe.controller.CrudController;
import com.web.recipe.model.entity.Article;
import com.web.recipe.model.network.request.ArticleApiRequest;
import com.web.recipe.model.network.response.ArticleApiResponse;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController extends CrudController<ArticleApiRequest, ArticleApiResponse, Article> {
}
