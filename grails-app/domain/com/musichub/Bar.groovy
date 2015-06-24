package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Bar extends MHUser{

	String name
	String description
	String address
	String phone
	String googlePlusId
	String facebookId
	String twitterId
	String website
	
	
	static hasMany = [
		videos: Video,
		photos: Photo,
		events: Event,
		postulations: Postulate
	]

	static mappedBy = [
		events: "bar",
		postulations: "offerer"
	]

	static mappings = {
		photos cascade: 'all-delete-orphan'
		videos cascade: 'all-delete-orphan'
		events cascade: 'all-delete-orphan'
	}

	static constraints = {
		name			nullable: true, blank: true
		description		nullable: true, blank: true, maxSize: 350
		address			nullable: true, blank: true, maxSize: 70
		phone			nullable: true, blank: true, minSize: 5, maxSize: 12
		googlePlusId	nullable: true, blank: true, minSize: 3, maxSize: 128
		facebookId		nullable: true, blank: true, minSize: 3, maxSize: 50
		twitterId		nullable: true, blank: true, minSize: 3, maxSize: 30
		website			nullable: true, blank: true, url: true
	}
}
