package com.musichub

class Bar extends MHUser{

	String name

	static mappings = {}

	static constraints = {
		name	blank: false
	}
}
