package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Country {
	String iso
	String name
	String nicename
	String iso3
	Integer numCode
	Integer phoneCode = 0
	
	static constraints = {
		iso			blank: false
		name		blank: false, minSize: 2, maxSize: 50
		nicename	blank: false, minSize: 2, maxSize: 50
		iso3		nullable: true, maxSize: 4
		numCode		nullable: true, maxSize: 4
		phoneCode	blank: false, maxSize: 4
	}
}
