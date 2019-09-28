package com.web.recipe.controller.api.recommendation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.recipe.controller.CrudController;
import com.web.recipe.model.entity.Recommendation;
import com.web.recipe.model.network.request.RecommendationApiRequest;
import com.web.recipe.model.network.response.RecommendationApiResponse;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationApiController extends CrudController<RecommendationApiRequest, RecommendationApiResponse, Recommendation> {

}
