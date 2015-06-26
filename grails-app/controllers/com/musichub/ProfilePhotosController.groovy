package com.musichub

import grails.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.google.api.client.http.HttpStatusCodes;
import com.musichub.util.google.services.Upload;

@Transactional(readOnly = true)
class ProfilePhotosController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth?.isAuthenticated()){
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
			if ( (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
				respond loggedUser.getPhotos()
			} else {
				render status: HttpStatus.NO_CONTENT
			}
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
	def show(Photo photo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth.isAuthenticated()){
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?: Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
			if ( (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
				respond loggedUser.photos.find {
					it == photo
				}
			} else {
				render status: HttpStatus.NOT_FOUND
			}
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
	@Transactional
	def save(Photo photo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
			params.fileId = uploadedFileId
			
			photo = new Photo(
				title: params.title,
				fileId: params.fileId
			)
		}
		if(!photo.hasErrors()) {
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
	
			if ( (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
				loggedUser.addToPhotos(photo)
				if(loggedUser.save(flush: true)){
					render status: HttpStatus.CREATED
				} else {
					respond loggedUser.errors
				}
			} else {
				render HttpStatus.UNPROCESSABLE_ENTITY
			}
		} else {
			respond photo.errors
		}
	}

	@Transactional
	def update(Photo photo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
			params.fileId = uploadedFileId
			
			photo = new Photo(
				title: params.title,
				fileId: params.fileId
			)
		}
		if(!photo.hasErrors()) {
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
	
			if ( (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
				loggedUser.addToPhotos(photo)
				if(loggedUser.save(flush: true)){
					render status: HttpStatus.CREATED
				} else {
					respond loggedUser.errors
				}
			} else {
				render HttpStatus.UNPROCESSABLE_ENTITY
			}
		} else {
			respond photo.errors
		}
	}

	@Transactional
	def delete(Photo photo) {
		if(!photo) {
			photo status: HttpStatus.NOT_FOUND
		}
		else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication()
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
			if ( (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
				loggedUser.removeFromPhotos(photo)
				photo.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			} else {
				render HttpStatus.UNPROCESSABLE_ENTITY
			}
		}
	}
}
