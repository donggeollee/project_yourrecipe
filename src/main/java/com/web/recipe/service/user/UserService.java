package com.web.recipe.service.user;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.recipe.model.entity.User;
import com.web.recipe.model.network.Header;
import com.web.recipe.repository.ArticleRepository;
import com.web.recipe.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Header idCheck(String account) {
		List<User> userList =  userRepository.findByAccount(account);

		if( userList.size() == 0 ) {
			// 중복 없음 .. 사용가능 
			return Header.OK("true");
		} else {
			return Header.OK("false");
		}
	}
	
	public Header nickNameCheck(String nickName) {
		List<User> userList =  userRepository.findByNickname(nickName);
		
		if( userList.size() == 0 ) {
			return Header.OK("true");
		} else {
			return Header.OK("false");
		}
	}
	
	public String login(User user,
						HttpServletResponse response,
						HttpSession session,
						Model model
						) {
		
		List<User> userList =  userRepository.findByAccount(user.getAccount());
		
		Cookie cookie = new Cookie("saveAccount",user.getAccount());
		
		if( userList.size() == 0 ) {
			model.addAttribute("loginResult", "noAccount");
 		} else if ( !userList.get(0).getPassword().equals(user.getPassword()) ) {
			model.addAttribute("loginResult", "noPassword");
		} else {
			
			if ( user.getRememberAccount() == null ) {
				cookie.setMaxAge(0);
			} else {
				cookie.setMaxAge(60*60*24*30);
			}
			response.addCookie(cookie);
			session.setAttribute("loginUser", userList.get(0));
		} 
		
		return "/user/login";
	}
	public String getMyLecipe(HttpSession session, Model model) {
		Optional<User> optional = userRepository.findById(((User)session.getAttribute("loginUser")).getId());
		optional.map(user -> model.addAttribute("articleList",user.getArticleList()));
		return "/user/myPage/myLecipe";
	} 
	public String getMyRecommend(HttpSession session, Model model) {
		Optional<User> optional = userRepository.findById(((User)session.getAttribute("loginUser")).getId());
		optional.map(user -> model.addAttribute("recommendationList",user.getRecommendationList()));
		return "/user/myPage/myRecommend";
	}
	
}
