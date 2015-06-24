package com.musichub

import org.springframework.http.HttpStatus;

import com.musichub.util.mail.services.MailService;

import grails.core.GrailsApplication;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class UsersController {
	static responseFormats = ['json', 'xml']
	
	def changePassword(MHUser user) {
		render status: HttpStatus.NOT_IMPLEMENTED
	}
	
	def confirmResetPasswordToken(String token) {
		if(token) {
			render token			
		} else {
			render status: HttpStatus.NOT_IMPLEMENTED, message: "TOKEN is invalid"
			
		}
	}

	@Transactional
	def register(MHUser user) {
		boolean userWasCreated = false
		if(!user.hasErrors()) {
			if(params.user_type.equals('bar')) {
				Bar bar = new Bar(
					username: user.getUsername(),
					email: user.getEmail(),
					password: user.getPassword()
				)
				if (bar.save(flush:true)) {
					UserRole userRole = new UserRole(
						user: bar,
						role: Role.findByAuthority('ROLE_BAR')
					).save(flush: true)
					userWasCreated = true
				} else {
					respond bar.errors
				}
			} else {
				Artist artist = new Artist(
					username: user.getUsername(),
					email: user.getEmail(),
					password: user.getPassword()
				)
				if(artist.save(flush:true)) {
					UserRole userRole = new UserRole(
						user: artist,
						role: Role.findByAuthority('ROLE_ARTIST')
					).save(flush: true)
					userWasCreated = true
				} else {
					respond artist.errors
				}
			}
		}
		if(userWasCreated){
			ApplicationContextHolder applicationContextHolder = ApplicationContextHolder.instance
			GrailsApplication ctx = applicationContextHolder.getGrailsApplication()
			MailService mailService = applicationContextHolder.getBean("mailService")
			mailService.sendMail(
				ctx.config.grails.mail.username,
				user.getEmail(),
				"Bienvenido ${user.getUsername()} - MusicHub",
				"Bienvenido ${user.getUsername()}, Ya podés empezar a compartir tu espacio con otros. Recordá que cualquier duda o consulta que tengas podés escribirnos a ${ctx.config.grails.mail.username}. ¡Gracias! El Equipo de MusicHub."
			)
			
			render status: HttpStatus.CREATED
		} else {
			respond user.errors
		}
	}
}
