package com.musichub

class Band {

	String name
	String slug
	Artist leader
	
	static belongsTo = Artist
	static hasMany = [artists:Artist]

    static constraints = {
		name	blank: false
		slug	blank: false, unique: true, minSize: 4, maxSize: 25
    }
}
