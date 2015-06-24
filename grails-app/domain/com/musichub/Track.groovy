package com.musichub

import com.musichub.domain.MediaType;

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Track extends MediaType{
	String name
	Integer order
	Disc disc

	static belongsTo = [Disc]

	static mappedBy = []

	static constraints = {
		name	blank: false, minSize: 2, maxSize: 30
		order	nullable: true, min: 1
	}
	
	static namedQueries = {
		findRandomTracksByLimit { int limit ->
			sqlRestriction "1=1 order by rand()"
			maxResults(limit ?: 5)
		}
	}
}
