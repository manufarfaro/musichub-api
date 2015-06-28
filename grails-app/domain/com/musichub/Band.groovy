package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Band {

	String name
	String bio
	Artist leader
	String slug
	String email
	String googlePlusId
	String facebookId
	String twitterId
	String website
	
	static hasMany = [
		artists: Artist,
		discs: Disc,
		videos: Video,
		photos: Photo,
		postulated: Postulate
	]
	
	static mappedBy = [
		postulated: "bandsPostulants",
		artists: "none"
	]
	
	static belongsTo = [Artist]

    static constraints = {
		name			blank: false
		bio				nullable: true, blank: true, maxSize: 350
		email	 		blank: false, email: true, unique: true
		leader			nullable: true
		slug			nullable: true, unique: true, minSize: 4, maxSize: 36
		googlePlusId	nullable: true, blank: true, minSize: 3, maxSize: 128
		facebookId		nullable: true, blank: true, minSize: 3, maxSize: 50
		twitterId		nullable: true, blank: true, minSize: 3, maxSize: 30
		website			nullable: true, blank: true, url: true
    }
	
	def mapping = {
		artists	column: "artist_id", joinTable: "artist_band", lazy: false
		photos cascade: 'all-delete-orphan'
	}

	def beforeValidate() {
		if (slug == "" || !slug) {
			this.slug = UUID.randomUUID().toString()
		}
	}
}
