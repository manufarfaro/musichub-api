package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class MHUser{

	transient springSecurityService

	String username
	String password
	String email
	String slug
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true, minSize: 8
		email	 email: true, blank: false, unique: true
		password blank: false, minSize: 8
		slug	 nullable: true, unique: true, minSize: 4, maxSize: 36
	}

	static mapping = {
		password column: '`password`'
		tablePerHierarchy false
//		reviews cascade: "all-delete-orphan"
//		table "mhusers"
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeValidate() {
		if (slug == "" || !slug) {
			this.slug = UUID.randomUUID().toString()
		}
	}
	
	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}