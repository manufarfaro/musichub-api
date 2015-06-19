package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Artist extends MHUser{

	String name
	String bio
	String lastname
	String gender
	Country region
	Date birthdate
	String codePhone
	String phone
	String googlePlusId
	String facebookId
	String twitterId
	String website

	static hasMany = [
		bands: Band,
		discs: Disc,
		videos: Video,
		photos: Photo
	]
	
	static mappings = {
		bands	column: "band", joinTable: "artist_band"
	}

	static constraints = {
		name			blank: false, minSize: 2, maxSize: 30
		bio				nullable: true, blank: true, maxSize: 350
		lastname		blank: false, minSize: 2, maxSize: 40
		gender			nullable: true, blank: true, minSize: 3, maxSize: 30
		region			nullable: true, blank: true
		birthdate		nullable: true, blank: true, min: new Date()
		codePhone		nullable: true, blank: true, minSize: 1, maxSize:5
		phone			nullable: true, blank: true, minSize: 5, maxSize: 12
		googlePlusId	nullable: true, blank: true, minSize: 3, maxSize: 128
		facebookId		nullable: true, blank: true, minSize: 3, maxSize: 50
		twitterId		nullable: true, blank: true, minSize: 3, maxSize: 30
		website			nullable: true, blank: true, url: true
	}
}
