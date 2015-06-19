package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Video extends MediaType{
	String title

	static constraints = {
		title	blank: false,minSize: 2, maxSize: 30
	}
}
