package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Disc {
	String name
	Band band
	Artist artist
	
	static belongsTo = [Artist, Band]

	static constraints = {
		name	blank: false,minSize: 2, maxSize: 50
	}

}
