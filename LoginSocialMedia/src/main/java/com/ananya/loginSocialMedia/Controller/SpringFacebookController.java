package com.ananya.loginSocialMedia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ananya.loginSocialMedia.Service.FacebookService;
import com.ananya.loginSocialMedia.model.UserInfo;

@Controller
public class SpringFacebookController {

	@Autowired private FacebookService facebookService;
	
	
	@GetMapping(value="/facebookLogin")
	public RedirectView facebookLogin() {
		RedirectView rv =new RedirectView();
		String url=facebookService.facebookLogin();
		System.out.println(url);
		rv.setUrl(url);
		return rv;
	}
	
	@GetMapping(value="/facebook")
	public String facebook(@RequestParam("code")String code) {
		String accessToken=facebookService.getFacebookAccessToken(code);
		System.out.println("***************************"+accessToken);
		return "redirect:/facebookprofiledata/"+accessToken;
		
	}
	@GetMapping(value="/facebookprofiledata/{accessToken}")
	public String facebookprofiledata(@PathVariable String accessToken,Model m) {
		User user=facebookService.getFacebookUserProfile(accessToken);
		System.out.println("***************************"+user.getFirstName());
		UserInfo u=new UserInfo(user.getFirstName(),user.getLastName(),user.getEmail());
		System.out.println("***************************"+u);
		m.addAttribute("user", u);
		return "view/userprofile";
		
	}
}
