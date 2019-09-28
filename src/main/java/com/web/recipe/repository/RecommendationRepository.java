package com.web.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Recommendation;
import com.web.recipe.model.entity.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
	List<Recommendation> findByArticle(Article article); 
	List<Recommendation> findByUser(User user);
}
