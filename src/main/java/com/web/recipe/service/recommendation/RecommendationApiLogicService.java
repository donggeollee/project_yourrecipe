package com.web.recipe.service.recommendation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Recommendation;
import com.web.recipe.model.entity.User;
import com.web.recipe.model.network.Header;
import com.web.recipe.model.network.request.RecommendationApiRequest;
import com.web.recipe.model.network.response.RecommendationApiResponse;
import com.web.recipe.service.BaseService;

@Service
public class RecommendationApiLogicService extends BaseService<RecommendationApiRequest, RecommendationApiResponse, Recommendation>{

	@Override
	public Header<RecommendationApiResponse> create(Header<RecommendationApiRequest> request) {
		RecommendationApiRequest recommendationApiRequest = request.getData();
		
		Recommendation recommendation = Recommendation.builder()
				.article(Article.builder().id(recommendationApiRequest.getArticleId()).build())
				.user(User.builder().id(recommendationApiRequest.getUserId()).build())
				.build();
		Recommendation newRecomm = baseRepository.save(recommendation);
		return response(newRecomm);
	}

	@Override
	public Header<RecommendationApiResponse> read(Long id) {
		return null;
	}

	@Override
	public Header<RecommendationApiResponse> update(Header<RecommendationApiRequest> request) {
		return null;
	}

	@Override
	public Header delete(Long id) {
		System.out.println("recommendation delete id : " + id);
		Optional<Recommendation> optional = baseRepository.findById(id);
		return optional.map(recommendation->{
			baseRepository.delete(recommendation);
			return Header.OK();
		})
		.orElseGet(()->Header.ERROR("ERROR"));
	}
	
	private Header<RecommendationApiResponse> response(Recommendation newRecomm){
		RecommendationApiResponse recommendationApiResponse = RecommendationApiResponse.builder()
				.id(newRecomm.getId())
				.articleId(newRecomm.getArticle().getId())
				.userId(newRecomm.getUser().getId())
				.build();
		return Header.OK(recommendationApiResponse);
	}


}
