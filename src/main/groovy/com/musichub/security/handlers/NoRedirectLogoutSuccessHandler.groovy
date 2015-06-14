package com.musichub.security.handlers

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

class NoRedirectLogoutSuccessHandler implements LogoutSuccessHandler {

	private final Log logger = LogFactory.getLog(getClass())

	@Override
	public void onLogoutSuccess(HttpServletRequest request,	HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{
		logger.debug("User has been logged out!")
		// no redirect !! (unlike @SimpleUrlLogoutSuccessHandler, that redirects after logout)
	}
}
