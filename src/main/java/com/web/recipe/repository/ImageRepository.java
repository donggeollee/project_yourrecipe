package com.web.recipe.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	
	List<Image> findByArticle(Article article);
}
