package com.musichub

class Band {

	String name
	Artist leader
	String slug
	
	static belongsTo = Artist
	static hasMany = [artists:Artist]

    static constraints = {
		name	blank: false
		slug	nullable: true, unique: true, minSize: 4, maxSize: 36
    }

	def beforeValidate() {
		if (slug == "" || !slug) {
			this.slug = UUID.randomUUID().toString()
		}
	}
}
