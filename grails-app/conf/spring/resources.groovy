import grails.rest.render.json.JsonRenderer;
import grails.rest.render.xml.XmlRenderer;

import com.musichub.ApplicationContextHolder;
import com.musichub.Artist;
import com.musichub.Band;
import com.musichub.Bar;
import com.musichub.MHUser;
import com.musichub.security.UserDetailsServiceImpl

import musichub.SecurityConfiguration

import com.musichub.security.GoogleAuth

// Place your Spring DSL code here
beans = {
	webSecurityConfiguration(SecurityConfiguration)
	UserDetailsService(UserDetailsServiceImpl)
	GoogleAuth(GoogleAuth)
	applicationContextHolder(ApplicationContextHolder) { bean ->
		bean.factoryMethod = 'getInstance'
	}
	mhUserRenderer(JsonRenderer, MHUser) {
		excludes = ['id', 'password', 'class']
	}
	artistRenderer(JsonRenderer, Artist) {
		excludes = ['id', 'password', 'class']
	}
	barRenderer(JsonRenderer, Bar) {
		excludes = ['id', 'password', 'class']
	}
	bandRenderer(JsonRenderer, Band) {
		excludes = ['id', 'class']
	}
}
