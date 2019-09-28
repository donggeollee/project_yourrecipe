package com.web.recipe.repository;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.User;
import java.util.List;
import java.lang.String;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	Page<Article> findByTitleContaining(String searchKeyword, Pageable pageable);
	List<Article> findByTitleContaining(String searchKeyword);
	List<Article> findByUser(User user);
	Page<Article> findAll(Pageable pageable);
}
