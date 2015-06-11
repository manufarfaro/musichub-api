package com.musichub

class Quote {
	String title
	String quote
	String fileId

	static constraints = {
		title		blank: false, maxSize: 50
		quote		blank:false, maxSize: 300
		fileId		blank:false, maxSize: 30
	}
}
