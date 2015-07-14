package com.musichub

import com.musichub.util.google.services.Upload;

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Quote {
	String title
	String quote
	byte[] file
	String fileId

	static transients = ['file']

	static constraints = {
		title		blank: false, maxSize: 50
		quote		blank:false, maxSize: 300
		fileId		blank:false, maxSize: 30
		file		maxSize: 1024 * 1024 * 3
	}

	static namedQueries = {
		findRandomQuotesByLimit { limit ->
			// PSQL specific random() function
			sqlRestriction "1=1 order by random()"
			maxResults(limit ?: 5)
		}
	}
}
