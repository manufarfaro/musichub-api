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
		format	nullable: true, maxSize: 50
		url		nullable: true, blank: true, maxSize: 400
	}

	static namedQueries = {
		findRandomVideosByLimit { limit ->
			// PSQL specific random() function
			sqlRestriction "1=1 order by random()"
			maxResults(limit ?: 5)
		}
	}
}
