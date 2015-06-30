package com.musichub

import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;
import com.musichub.util.mail.services.MailService;

import grails.core.GrailsApplication;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class UsersController {
	static responseFormats = ['json', 'xml']
	
	@Transactional
	def resetPassword(MHUser user) {
		println "params: ${params}"
		user  =  MHUser.withCriteria {
		  or {
		    eq('id', params.long('id'))
		    eq('email', params.id)
			eq('username', params.id)
		  }
		  maxResults 1
		}[0]
		println "user: ${user}"
		if (user) {
			String token = UUID.randomUUID().toString()
			user.setPasswordResetToken(token)
			user.save(flush: true)

			ApplicationContextHolder applicationContextHolder = ApplicationContextHolder.instance
			GrailsApplication ctx = applicationContextHolder.getGrailsApplication()
			MailService mailService = applicationContextHolder.getBean("mailService")
			mailService.sendMail(
				ctx.config.grails.mail.username,
				user.getEmail(),
				"Solicitud de cambio de contraseña para ${user.getUsername()}",
				"Hola ${user.getUsername()},\n Hemos recibido una solicitud de cambio de contraseña para tu cuenta. \n\nEl código es: ${token}\n\n Acordate que éste código debés ingresarlo en el sitio en la página de ¿Olvidaste tu contraseña? para poder recibir una nueva contraseña temporal. \nSi no fuiste vos quien realizó este trámite, simplemente ignorá este mensaje. \nRecordá que cualquier duda o consulta que tengas podés escribirnos a ${ctx.config.grails.mail.username}.\n ¡Gracias! El Equipo de MusicHub."
			)
			render status: HttpStatus.CREATED
		} else {
			render status: HttpStatus.NOT_FOUND
		}
	}

	@Transactional
	def confirmResetPasswordToken(String token) {
		if(token) {
			MHUser user = MHUser.findByPasswordResetToken(token)
			if (user) {
				String password = UUID.randomUUID().toString()
				user.setPassword(password)
				user.setPasswordResetToken(null)
				if (!user.hasErrors()) {
					user.save(flush: true)

					ApplicationContextHolder applicationContextHolder = ApplicationContextHolder.instance
					GrailsApplication ctx = applicationContextHolder.getGrailsApplication()
					MailService mailService = applicationContextHolder.getBean("mailService")
					mailService.sendMail(
						ctx.config.grails.mail.username,
						user.getEmail(),
						"Solicitud de cambio de contraseña para ${user.getUsername()}",
						"Hola ${user.getUsername()},\n Hemos procesado correctamente la solicitud de cambio de contraseña para tu cuenta. \n\nTu nueva contraseña es: ${user.getPassword()}\n\n Te recomendamos cambiar la contraseña una vez que puedas volver a entrar. \nRecordá que cualquier duda o consulta que tengas podés escribirnos a ${ctx.config.grails.mail.username}. \n¡Gracias! El Equipo de MusicHub."
					)

					render status: HttpStatus.CREATED
				} else {
					respond user.errors
				}
			} else {
				render status: HttpStatus.OK, message: [ errors: [ message: 'El token o Usuario proporcionado es inválido.'] ]
			}
		} else {
			render status: HttpStatus.NOT_FOUND
			
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
	
	@Transactional
	def changePassword() {
		def loggedUser = UserUtils.getLoggedUser()
		loggedUser.setPassword(params.password)
		if (!loggedUser.hasErrors()) {
			loggedUser.save(flush: true)
			render status: HttpStatus.CREATED
		} else {
			respond loggedUser.errors
		}
	}
}
