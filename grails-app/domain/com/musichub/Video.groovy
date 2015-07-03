package com.musichub

import com.musichub.domain.MediaType;

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Video extends MediaType{
	String title
	String format
	String url

	static constraints = {
		title	blank: false, minSize: 2, maxSize: 30
		format	nullable: true, maxSize: 10
		url		nullable: true, blank: true, url: true
	}
}
