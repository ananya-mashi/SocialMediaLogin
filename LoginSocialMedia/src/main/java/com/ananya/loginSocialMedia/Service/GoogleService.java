package com.ananya.loginSocialMedia.Service;

import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.plus.Person;

public interface GoogleService {

	String googleLogin();

	String getGoogleAccessToken(String code);

	Person getGoogleUserProfile(String accessToken);

}
