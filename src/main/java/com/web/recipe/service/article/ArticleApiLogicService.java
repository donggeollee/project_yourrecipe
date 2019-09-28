package com.web.recipe.service.article;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Comment;
import com.web.recipe.model.entity.Image;
import com.web.recipe.model.entity.Recommendation;
import com.web.recipe.model.network.Header;
import com.web.recipe.model.network.request.ArticleApiRequest;
import com.web.recipe.model.network.response.ArticleApiResponse;
import com.web.recipe.repository.CommentRepository;
import com.web.recipe.repository.ImageRepository;
import com.web.recipe.repository.RecommendationRepository;
import com.web.recipe.service.BaseService;


@Service
public class ArticleApiLogicService extends BaseService<ArticleApiRequest, ArticleApiResponse, Article>{
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	RecommendationRepository recommendationRepository;
	
	@Override 
	public Header<ArticleApiResponse> create(Header<ArticleApiRequest> request) {
		return null;
	}

	@Override
	public Header<ArticleApiResponse> read(Long id) {
		Optional<Article> optional = baseRepository.findById(id);
		return optional
				.map(article-> response(article))
				.orElseGet(()-> null);
	}

	@Override
	public Header<ArticleApiResponse> update(Header<ArticleApiRequest> request) {
		return null;
	}

	@Override
	public Header delete(Long id) {
		Optional<Article> optional = baseRepository.findById(id);

		return optional.map(article->{
			// 참조 테이블 삭제 : Image
			List<Image> imageList = 
					imageRepository.findByArticle(Article.builder().id(article.getId()).build());
			for( Image image : imageList ) {
				System.out.println("image.id : "+ image.getId() +" 삭제 완료");
				imageRepository.delete(image);
			}
			// 참조 테이블 삭제 : Comment
			List<Comment> commnetList = 
					commentRepository.findByArticle(Article.builder().id(article.getId()).build());
			for( Comment comment : commnetList ) {
				System.out.println("comment.id : "+ comment.getId() +" 삭제 완료");
				commentRepository.delete(comment);
			}
			// 참조 테이블 삭제 : Recommendation
			List<Recommendation> recommendationList = 
					recommendationRepository.findByArticle(Article.builder().id(article.getId()).build());
			for ( Recommendation recommendation : recommendationList ) {
				System.out.println("recommendation.id : "+ recommendation.getId() +" 삭제 완료");
				recommendationRepository.delete(recommendation);
			}
			baseRepository.delete(article);
			return Header.OK();
		}).orElseGet(()->Header.ERROR("ERROR"));
	}
	
	private Header<ArticleApiResponse> response(Article article){
		return Header.OK(ArticleApiResponse.builder()
					.id(article.getId())
					.title(article.getTitle())
					.content(article.getTitle())
					.createdAt(article.getCreatedAt())
					.userId(article.getUser().getId())
					.build());
	}

}
