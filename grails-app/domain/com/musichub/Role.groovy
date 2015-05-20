package com.musichub

class Role {

	String authority

	static mapping = {
		cache true
		reviews cascade: "all-delete-orphan"
	}

	static constraints = {
		authority blank: false, unique: true
	}
}