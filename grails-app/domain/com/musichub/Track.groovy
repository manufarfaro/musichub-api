package com.musichub

import com.musichub.domain.MediaType;

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Track extends MediaType{
	String name
	Integer orderNbr
	Disc disc
	String format
	String url

	static belongsTo = [Disc]

	static mappedBy = [
		disc: "tracks"
	]

	static constraints = {
		name	blank: false, minSize: 2, maxSize: 30
		orderNbr	nullable: true, unique: false
		format	nullable: true, maxSize: 50
		url		nullable: true, blank: true, maxSize: 400
	}
	
	static namedQueries = {
		findRandomTracksByLimit { limit ->
			// PSQL specific random() function
			sqlRestriction "1=1 order by random()"
			maxResults(limit ?: 5)
		}
	}
}
