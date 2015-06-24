package com.musichub

class Event {
	String name
	Date date
	String text
	Photo photo
	String url
	Bar bar
	
	static belongsTo = [Bar]

	static mappedBy = [bar: "events"]

	static constraints = {
		name 	blank: false
		text 	nullable: true, blank: true, maxSize: 350
		date 	blank: false, min: new Date()
		photo	nullable:true
	}
}
