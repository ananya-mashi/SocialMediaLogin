package com.ananya.loginSocialMedia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ananya.loginSocialMedia.Service.GoogleService;
import com.ananya.loginSocialMedia.model.UserInfo;
@Controller
public class SpringGoogleController {

	@Autowired private GoogleService googleService;
	
	
	@GetMapping(value="/googleLogin")
	public RedirectView googleLogin() {
		RedirectView rv =new RedirectView();
		String url=googleService.googleLogin();
		System.out.println(url);
		rv.setUrl(url);
		return rv;
	}
	
	@GetMapping(value="/google")
	public String google(@RequestParam("code")String code) {
		String accessToken=googleService.getGoogleAccessToken(code);
		System.out.println("***************************"+accessToken);
		return "redirect:/googleprofiledata/"+accessToken;
		
	}
	@GetMapping(value="/googleprofiledata/{accessToken}")
	public String googleprofiledata(@PathVariable String accessToken,Model m) {
		Person user=googleService.getGoogleUserProfile(accessToken);
		System.out.println("***************************"+user);
		UserInfo u=new UserInfo(user.getGivenName(),user.getFamilyName(),user.getAccountEmail());
		System.out.println("***************************"+u);
		m.addAttribute("user", u);
		return "view/userprofile";
	}	
	
}
