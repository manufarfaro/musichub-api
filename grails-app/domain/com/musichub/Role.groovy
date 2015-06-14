package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
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