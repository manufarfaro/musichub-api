import org.springframework.mail.javamail.JavaMailSenderImpl

import grails.rest.render.json.JsonRenderer
import grails.rest.render.xml.XmlRenderer

import com.musichub.ApplicationContextHolder
import com.musichub.Artist;
import com.musichub.Band;
import com.musichub.Bar;
import com.musichub.MHUser;
import com.musichub.security.UserDetailsServiceImpl

import musichub.SecurityConfiguration

import com.musichub.security.GoogleAuth
import com.musichub.util.mail.services.MailService;

// Place your Spring DSL code here
beans = {
	grailsApplication = ref('grailsApplication')
	webSecurityConfiguration(SecurityConfiguration)
	UserDetailsService(UserDetailsServiceImpl)
	GoogleAuth(GoogleAuth)
	mailSender(JavaMailSenderImpl) {
		host = grailsApplication.config.grails.mail.sender.host
		port = grailsApplication.config.grails.mail.sender.port
		username = grailsApplication.config.grails.mail.sender.username
		password = grailsApplication.config.grails.mail.sender.password
		javaMailProperties = {
			mail.smtp.auth = grailsApplication.config.grails.mail.sender.smtp.auth
			mail.smtp.starttls.enable = grailsApplication.config.grails.mail.sender.smtp.starttls.enable
		}
	}

	mailService(MailService) {
		mailSender = ref('mailSender')
	}

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
