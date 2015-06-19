package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Track {
	String name
	int order
	Disc disc

	static belongsTo = [Disc] 
	
	static constraints = {
		name	blank: false,minSize: 2, maxSize: 30
	}
}
