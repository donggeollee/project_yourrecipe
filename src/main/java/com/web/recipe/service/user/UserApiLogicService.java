package com.web.recipe.service.user;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Comment;
import com.web.recipe.model.entity.Image;
import com.web.recipe.model.entity.Recommendation;
import com.web.recipe.model.entity.User;
import com.web.recipe.model.network.Header;
import com.web.recipe.model.network.request.UserApiRequest;
import com.web.recipe.model.network.response.UserApiResponse;
import com.web.recipe.repository.ArticleRepository;
import com.web.recipe.repository.CommentRepository;
import com.web.recipe.repository.ImageRepository;
import com.web.recipe.repository.RecommendationRepository;
import com.web.recipe.service.BaseService;
	
@Service
@Primary
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User>{
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	RecommendationRepository recommendationRepository;
	@Autowired
	ImageRepository imageRepository; 
	@Autowired
	HttpSession session;

	@Override
	public Header<UserApiResponse> create(Header<UserApiRequest> request) {
		UserApiRequest userApiRequest = request.getData();
		
		User user = User.builder()
				.account(userApiRequest.getAccount().trim())
				.password(userApiRequest.getPassword().trim())
				.nickname(userApiRequest.getNickname().trim())
				.gender(userApiRequest.getGender())
				.build();
		
		User newUser = baseRepository.save(user);
		return response(newUser);
	}

	@Override
	public Header<UserApiResponse> read(Long id) {
		return null;
	}
	
	@Override
	public Header<UserApiResponse> update(Header<UserApiRequest> request) {
		
		UserApiRequest userApiRequest = request.getData();
		
		Optional<User> optional = baseRepository.findById(userApiRequest.getId());
		
		return optional.map(user ->{
			user.setPassword(userApiRequest.getPassword())
			.setNickname(userApiRequest.getNickname());
			return user;
		})
				.map(user -> baseRepository.save(user))
				.map(updateUser -> response(updateUser))
				.orElseGet(()->Header.ERROR("ERROR"));
	}
	@Override
	public Header delete(Long id) {
		Optional<User> optional = baseRepository.findById(id);
		return optional.map(user->{
			// articleRepository;
			// commentRepository;
			// recommendationRepository;
			
			// 참조 테이블 삭제 : Article
			List<Article> articleList = 
					articleRepository.findByUser(User.builder().id(user.getId()).build());
			
			for( Article article : articleList ) {
				List<Image> imageList = imageRepository.findByArticle(article);
				// Article을 참조하고 있는 Image 삭제
				for( Image image : imageList ) {
					System.out.printf("article(%s),image(%s) 삭제\n",article.getTitle(),image.getId() );
					imageRepository.delete(image);
				}
				articleRepository.delete(article);
				
			}
			// 참조 테이블 삭제 : Comment
			List<Comment> commentList =
					commentRepository.findByUser(User.builder().id(user.getId()).build());
			for( Comment comment: commentList ) {
				System.out.println("comment.id : "+ comment.getId() +" 삭제 완료");
				commentRepository.delete(comment);
			}
			// 참조 테이블 삭제 : Recommendation
			List<Recommendation> recommendationList = 
					recommendationRepository.findByUser(User.builder().id(user.getId()).build());
			for( Recommendation recommendation: recommendationList ) {
				System.out.println("recommendation.id : "+ recommendation.getId() +" 삭제 완료");
				recommendationRepository.delete(recommendation);
			}
			baseRepository.delete(user);
			session.invalidate();
			return Header.OK(); 
		}).orElseGet(()->Header.ERROR("ERROR"));
	}

	
	// user -> Header(userApiResponse)
	public static Header<UserApiResponse> response(User user){
		
		UserApiResponse userApiResponse = UserApiResponse.builder()
				.id(user.getId())
				.account(user.getAccount())
				.nickname(user.getNickname())
				.gender(user.getGender())
				.createdAt(user.getCreatedAt())
				.build();
		
		return Header.OK(userApiResponse);
	}

	
}
