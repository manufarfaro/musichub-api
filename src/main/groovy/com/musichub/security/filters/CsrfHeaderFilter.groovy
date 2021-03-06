package com.musichub.security.filters

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.grails.web.util.WebUtils;
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.filter.OncePerRequestFilter

class CsrfHeaderFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
		HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	  /* Workaround to make things work!
	  CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
		  .getName());
	  if (csrf != null) {
		Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
		String token = csrf.getToken();
		if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
		  cookie = new Cookie("XSRF-TOKEN", token)
		  cookie.setPath("/")
		  cookie.setHttpOnly(false)
		  response.addCookie(cookie)
		}
	  }
	  filterChain.doFilter(request, response);
	  */
	}

}
