package com.musichub

class Postulate {

	String title
	String text
	MHUser offerer

	static belongsTo = [MHUser, Artist, Bar, Band]

	static hasMany = [
		artistsPostulants : Artist,
		bandsPostulants: Band
	]
	static mappedBy = [
		artistsPostulants: "postulated",
		bandsPostulants: "postulated"
	]
}
