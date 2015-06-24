package com.musichub

class Postulate {

	String title
	String text
	MHUser offerer

	static belongsTo = [MHUser, Artist, Band]

	static hasMany = [
		artistsPostulants : Artist,
		bandsPostulants: Band
	]
	static mappedBy = [
		artistsPostulants: "postulations",
		bandsPostulants: "postulations"
	]
}
