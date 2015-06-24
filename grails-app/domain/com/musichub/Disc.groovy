package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Disc {
	String name
	Band band
	Artist artist
	Photo artwork
	
	static belongsTo = [Artist, Band]

	static constraints = {
		name	blank: false,minSize: 2, maxSize: 50
		artwork	nullable:true
	}

}
