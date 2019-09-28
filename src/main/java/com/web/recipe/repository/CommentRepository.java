package com.web.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.recipe.model.entity.Comment;
import com.web.recipe.model.entity.Article;
import java.util.List;
import com.web.recipe.model.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByUser(User user);
	List<Comment> findByArticle(Article article);
}
