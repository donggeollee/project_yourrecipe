package com.web.recipe.service.comment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Comment;
import com.web.recipe.model.entity.User;
import com.web.recipe.model.network.Header;
import com.web.recipe.model.network.request.CommentApiRequest;
import com.web.recipe.model.network.response.CommentApiResponse;
import com.web.recipe.repository.UserRepository;
import com.web.recipe.service.BaseService;

@Service 
public class CommentApiLogicService extends BaseService<CommentApiRequest, CommentApiResponse, Comment>{

	@Autowired
	UserRepository userRepository;
	
	@Override 
	public Header<CommentApiResponse> create(Header<CommentApiRequest> request) {
		CommentApiRequest commentApiRequest = request.getData();
		Comment comment = Comment.builder()
				.content(commentApiRequest.getContent())
				.article(Article.builder().id(commentApiRequest.getArticleId()).build())
				.user(User.builder().id(commentApiRequest.getUserId()).build())
				.build();
				
		Comment newComment = baseRepository.save(comment);
		return response(newComment);
	}

	@Override
	public Header<CommentApiResponse> read(Long id) {
		return null;
	}

	@Override
	public Header<CommentApiResponse> update(Header<CommentApiRequest> request) {
		
		CommentApiRequest commentApiRequest = request.getData();
		
		Optional<Comment> optional = baseRepository.findById(commentApiRequest.getId());
		
		return optional.map(comment->{
			
			comment.setContent(commentApiRequest.getContent());
			return comment;
		})
				.map(comment->baseRepository.save(comment))
				.map(updateComment->response(updateComment))
		.orElseGet(
				()->Header.ERROR("데이터없음")
		);
				
	}

	@Override
	public Header delete(Long id) {
		Optional<Comment> optional = baseRepository.findById(id);
		
		return optional.map(comment ->{
			 baseRepository.delete(comment);
			 return Header.OK();
		})
		.orElseGet(()->Header.ERROR("데이터없음"));
	}
	private Header<CommentApiResponse> response(Comment comment){
		CommentApiResponse commentApiResponse = CommentApiResponse.builder()
				.content(comment.getContent()) 
				.createdAt(comment.getCreatedAt())
				.build();
		
		return Header.OK(commentApiResponse); 
	}

}
