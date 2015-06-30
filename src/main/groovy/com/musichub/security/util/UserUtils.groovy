package com.musichub.security.util

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.musichub.Artist;
import com.musichub.Bar;
import com.musichub.MHUser;

class UserUtils {
	static def getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		def returnUser
		if (auth?.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")){
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			def loggedUser = Artist.get(mhUser.getId()) ?: Bar.get(mhUser.getId())
			returnUser = loggedUser ?: mhUser
		}
		returnUser
	}
}
