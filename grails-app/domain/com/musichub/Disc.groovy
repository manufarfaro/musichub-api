package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Disc {
	String name
	Band band
	Artist artist
	Photo artwork
	
	static hasMany = [
		tracks: Track
	]
	
	static mappedBy = [
		tracks: "disc"
	]
	
	static belongsTo = [Artist, Band]

	static constraints = {
		name	blank: false,minSize: 2, maxSize: 50
		band	nullable:true
		artist	nullable:true
		artwork	nullable:true
	}

}
