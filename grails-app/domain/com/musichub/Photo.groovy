package com.musichub

import groovy.transform.ToString;

import com.musichub.domain.MediaType;

@ToString(includeNames=true, includeFields=true)
class Photo extends MediaType{
	String title

	static constraints = {
		title	blank: false,minSize: 2, maxSize: 30
	}

}
