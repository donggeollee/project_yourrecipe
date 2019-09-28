package com.web.recipe.controller.api.article;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.recipe.model.entity.Article;
import com.web.recipe.service.article.ArticleService;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/") 
	public String articleList(Model model) {
		return articleService.readAll(model,1);
	}
	
	@GetMapping("/{page}") 
	public String articleList(Model model,@PathVariable int page) {
		return articleService.readAll(model,page);
	}
	@GetMapping("/{page}/{searchKeyword}") 
	public String articleListBySearch(Model model, @PathVariable int page,
			@PathVariable String searchKeyword) {
		System.out.println("searchKeyword : " + searchKeyword);
		return articleService.readListBySearch(model,page,searchKeyword);
	}
	
	@GetMapping("/article/write")
	public String aticleWriteGet() {
		return "/article/articleWrite";
	}
	
	@PostMapping("/article/write")
	public String articleWrite( MultipartHttpServletRequest mpr, 
			Article arcticle,Model model) throws IOException {
		return articleService.write(mpr, arcticle ,model );
	}
	
	@GetMapping("/article/detail/{id}")
	public String articleDetail(@PathVariable Long id,
			Model model,
			HttpSession session) { 
		return articleService.readArticle(id, model);
	}
	
	@GetMapping("/article/update/{id}")
	public String articleGetUpdate(@PathVariable Long id,
			Model model,
			HttpSession session) { 
		return articleService.readUpdateArticle(id, model);
	}
	
	@PostMapping("/article/update")
	public String articleUpdate(MultipartHttpServletRequest mpr,
			Article article, Model model) throws IOException {
		return articleService.update(mpr,article,model);
	}
}
