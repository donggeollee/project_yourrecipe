package com.web.recipe.service.article;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.recipe.component.PagingInfo;
import com.web.recipe.model.entity.Article;
import com.web.recipe.model.entity.Comment;
import com.web.recipe.model.entity.Image;
import com.web.recipe.model.entity.Recommendation;
import com.web.recipe.model.entity.User;
import com.web.recipe.repository.ArticleRepository;
import com.web.recipe.repository.CommentRepository;
import com.web.recipe.repository.ImageRepository;
import com.web.recipe.repository.RecommendationRepository;
import com.web.recipe.repository.UserRepository;

@Service
public class ArticleService {
	
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	
	public String readArticle(Long id, Model model) {
		Optional<Article> optional = articleRepository.findById(id); 
		
		System.out.println("articleDetail id : " + id);
		return optional.map(article ->{
			
			Long readCount = null;
			if ( article.getReadCount() == null) 
				readCount = 0L;
			else 
				readCount = article.getReadCount();
			
			article.setReadCount(readCount + 1L); 
			Article modelArticle = articleRepository.save(article);
			
			model.addAttribute("article", modelArticle);
			
			return "/article/articleDetail";
		}).orElseGet(()->"/article/articleDetail");
	}
	
	public String readUpdateArticle(Long id, Model model) {
		Optional<Article> optional = articleRepository.findById(id); 
		return optional.map(article->{
			model.addAttribute("article",article);
			return "/article/articleUpdate";
		}).orElseGet(()->"/article/articleUpdate");
	}
	
	public String readAll(Model model,int page) {
		HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
		
		Pageable pageable = PageRequest.of(page-1, PagingInfo.PAGESIZE);
		Page<Article> articlePage = articleRepository.findAll(pageable);
		List<Article> articleList= articlePage.getContent();
		
		pageMap = pageUtil(page,null);
		
		model.addAttribute("pageMap",pageMap);  
		model.addAttribute("articleList", articleList); 
		return "/article/articleList";
	}	
	
	public String readListBySearch(Model model,int page, String searchKeyword) {
		HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
		
		Pageable pageable = PageRequest.of(page-1, PagingInfo.PAGESIZE);
		Page<Article> articlePage = articleRepository.findByTitleContaining(searchKeyword, pageable);
		List<Article> articleList = articlePage.getContent();
		
		pageMap = pageUtil(page,searchKeyword);
		
		model.addAttribute("searchKeyword",searchKeyword);
		model.addAttribute("pageMap",pageMap);  
		model.addAttribute("articleList", articleList); 
		return "/article/articleList";
	}
	
	public String write(MultipartHttpServletRequest mpr
			, Article article
			, Model model) throws IOException {
		
		List<MultipartFile> fileList = mpr.getFiles("file");
		String realPath = mpr.getSession().getServletContext().getRealPath("/resources/img");
		String savedPath = calcPath(realPath);
		UUID uid = UUID.randomUUID();
		
		Article newArticle = null;
		
		int i = 0;
		for ( MultipartFile file : fileList) { 
			i++;
			String systemSavedName = uid.toString() + "_" + file.getOriginalFilename();
			
			// 파일시스템이 저장할 경로
			File copyFile = new File(realPath + savedPath, systemSavedName);
			
			// jsp에서 불러올때 사용할 경로로 변경 -> db 저장
			String temp = savedPath + File.separator + systemSavedName;
			String dbSavedName= temp.replaceAll("\\\\", "/");
			
			FileCopyUtils.copy(file.getBytes(), copyFile);
			
			if ( !systemSavedName.endsWith(".jpg") && !systemSavedName.endsWith(".png") 
					&& !systemSavedName.endsWith(".jpeg")) {
				continue;
			} else if ( i == 1 ){
				String thumbnailName = makeThumbnail(realPath, savedPath, systemSavedName);
				
				User tempUser = (User)(mpr.getSession().getAttribute("loginUser"));
				List<User> userList = userRepository.findByAccount(tempUser.getAccount());
				article.setThumbnailName(thumbnailName).setUser(userList.get(0)); 
				newArticle = articleRepository.save(article);
			}
			
			 Image image = Image.builder()
					  .imageName(dbSavedName) 
					  .article(newArticle) 
					  .build();
			 
			 imageRepository.save(image);
		}
		
		return "redirect:/1"; 
	}
	public String update(MultipartHttpServletRequest mpr,
			Article article, Model model) throws IOException {
		List<MultipartFile> fileList = mpr.getFiles("file");
		String realPath = mpr.getSession().getServletContext().getRealPath("/resources/img");
		String savedPath = calcPath(realPath);
		UUID uid = UUID.randomUUID();
		
		Optional<Article> optional= articleRepository.findById(article.getId());
		Article updateArticle = optional.map(temp->{
			return temp; 
		}).orElseGet(()->null);
		
		int i = 0;
		for ( MultipartFile file : fileList) { 
			i++;
			String systemSavedName = uid.toString() + "_" + file.getOriginalFilename();
			
			// 파일시스템이 저장할 경로
			File copyFile = new File(realPath + savedPath, systemSavedName);
			
			// jsp에서 불러올때 사용할 경로로 변경 -> db 저장
			String temp = savedPath + File.separator + systemSavedName;
			String dbSavedName= temp.replaceAll("\\\\", "/");
			
			FileCopyUtils.copy(file.getBytes(), copyFile);
			
			if ( !systemSavedName.endsWith(".jpg") && !systemSavedName.endsWith(".png") 
					&& !systemSavedName.endsWith(".jpeg")) {
				continue;
			} else if ( i == 1 ){
				String thumbnailName = makeThumbnail(realPath, savedPath, systemSavedName);
				
				updateArticle.setThumbnailName(thumbnailName)
				.setTitle(article.getTitle())
				.setContent(article.getContent());
				articleRepository.save(updateArticle);
			}
			
			 Image image = Image.builder()
					  .imageName(dbSavedName) 
					  .article(updateArticle) 
					  .build();
			 
			 imageRepository.save(image);
		}
		
		return "redirect:/user/myPage/myLecipe";
	}
	
	private HashMap<String, Integer> pageUtil(int page,String searchKeyword){
		HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
		
		
		int curPage = page;
		int totalArticleCount = searchKeyword == null ? 
					(int)articleRepository.count() :  
					(int)articleRepository.findByTitleContaining(searchKeyword).size();
		int totalPageCount = totalArticleCount % PagingInfo.PAGESIZE == 0 ? 
							totalArticleCount / PagingInfo.PAGESIZE : 
							totalArticleCount / PagingInfo.PAGESIZE + 1;
		int startPage = (page % PagingInfo.PAGEWIDTH == 0 ? page-1 : page) 
						/ PagingInfo.PAGEWIDTH  * PagingInfo.PAGEWIDTH + 1;
		int endPage = startPage + PagingInfo.PAGEWIDTH - 1 ;
		
		if ( endPage > totalPageCount ) {
			endPage = totalPageCount;
		}
				
		int previousPage = startPage == 1 ? -1 : startPage - 1;
		int nextPage = endPage < totalPageCount ? endPage + 1 : -1 ;
		
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		pageMap.put("previousPage", previousPage);
		pageMap.put("nextPage", nextPage);
		pageMap.put("curPage", curPage);
		
		return pageMap;
	}
	
	// 날짜별로 파일 저장
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath +
				File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

		String datePath = monthPath + 
				File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath,yearPath,monthPath,datePath);
		return datePath;
	}
	
	// 날짜별 폴더 생성. 이미 존재하면 스킵
	private static void makeDir(String uploadPath, String...paths) {
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for ( String path : paths ) {
			File dirPath = new File(uploadPath + path);
			
			if( !dirPath.exists()) {
				dirPath.mkdir(); 
			}
		}
	}
	
	// 썸네일 이미지 별도 생성
	private static String makeThumbnail(
			String uploadPath, String path, String fileName) throws IOException {
		
		BufferedImage sourceImg = 
				ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(
				sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(destImg, formatName, newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
