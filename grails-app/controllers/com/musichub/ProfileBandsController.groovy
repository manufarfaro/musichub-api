package com.musichub

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.musichub.security.util.UserUtils;

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class ProfileBandsController {
	static responseFormats = ['json', 'xml']

	def index() {
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.authorities?.find { it.equals('ROLE_ADMIN') })  {
			render (contentType: "application/json") {
				[]
			}
		}
		if (loggedUser) {
			render (contentType: "application/json") {
				[
					loggedUser.getBands().collect {
						[
							id: it.id,
							name: it.name,
							artists: it.artists.collect {
								[
									class: "com.musichub.Artists",
									id : it.id
								]
							},
							bio: it.bio,
							leader: [
								class: "com.musichub.Artists",
								id: it.leader.id
							],
							discs: it.discs,
							videos: it.videos,
							photos: it.photos,
							slug: it.slug,
							email: it.email,
							googlePlusId: it.googlePlusId,
							facebookId: it.facebookId,
							twitterId: it.twitterId,
							website: it.website
						]
					}
				]
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}
