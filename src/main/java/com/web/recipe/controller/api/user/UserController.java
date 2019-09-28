package com.web.recipe.controller.api.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.recipe.model.entity.User;
import com.web.recipe.model.network.Header;
import com.web.recipe.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/login")
	public String home(@CookieValue(name = "saveAccount",required = false)Cookie cookie,
			Model model , HttpServletRequest request) {
		 HttpSession session = request.getSession(false);
		 
		 if ( session.getAttribute("loginUser") != null ) {
			 session.invalidate();
		 }
		
		 if ( cookie != null ) { 
			 System.out.println("save : " + cookie.getValue()); 
			 model.addAttribute("saveAccount", cookie.getValue()); 
		 }
		return "/user/login";
	}
	@GetMapping("/user/regist")  
	public String regist() {
		return "/user/regist";
	}

	@GetMapping("/user/myPage/myInfo")
	public String updateMyInfo(
			HttpSession session,
			Model model) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute("user",user);
		return "/user/myPage/myInfo";  
	}
	@GetMapping("/user/myPage/myLecipe")
	public String updateMyLecipe(HttpSession session, Model model) {
		return  userService.getMyLecipe(session,model);
	}
	@GetMapping("/user/myPage/myRecommend")
	public String updateMyRecommend(HttpSession session, Model model) {
		return userService.getMyRecommend(session, model); 
	}
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("loginUser") != null)
			session.invalidate();
		return "redirect:/1";
	}

	@ResponseBody
	@PostMapping("/api/user/idCheck")
	public Header idCheck(@RequestBody String account) {
		return userService.idCheck(account);
	} 
	
	@ResponseBody 
	@PostMapping("/api/user/nickCheck")
	public Header nickCheck(@RequestBody String nickName) {
		return userService.nickNameCheck(nickName);
	}
	
	@PostMapping("/api/user/login")
	public String login(User user,
			HttpServletResponse response,
			HttpSession session,
			Model model) { 
		return userService.login(user, response, session, model);
	}

}
