package com.ananya.loginSocialMedia.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
@Service
public class GoogleServiceImpl implements GoogleService {
	@Value("${spring.social.google.app-id}")
private String googleId;
	@Value("${spring.social.google.app-secret}")
private String googleSecret;

	private GoogleConnectionFactory createGoogleConnection() {
		// TODO Auto-generated method stub
		return new GoogleConnectionFactory(googleId,googleSecret);
	}

	@Override
	public String googleLogin() {
		OAuth2Parameters p=new OAuth2Parameters();
		p.setRedirectUri("http://localhost:8080/google");
		p.setScope("profile");
		return createGoogleConnection().getOAuthOperations().buildAuthenticateUrl(p);
	
	}

	@Override
	public String getGoogleAccessToken(String code) {
		return createGoogleConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/google", null).getAccessToken();
		}

	@Override
	public Person getGoogleUserProfile(String accessToken) {
Google google=new GoogleTemplate(accessToken);
Person p=google.plusOperations().getGoogleProfile();
		return p;
	}
}
