class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
		group "/quotes", {
			"/"		(controller: "quotes", action: "index", method: "GET")
			"/$id"	(controller: "quotes", action: "show", method: "GET")
			"/"		(controller: "quotes", action: "save", method: "POST")
			"/$id"	(controller: "quotes", action: "update", method: "PUT")
			"/$id"	(controller: "quotes", action: "delete", method: "DELETE")
		}

		group "/country", {
			"/"		(controller: "country", action: "index", method: "GET")
			"/$id"	(controller: "country", action: "show", method: "GET")
			"/"		(controller: "country", action: "save", method: "POST")
			"/$id"	(controller: "country", action: "update", method: "PUT")
			"/$id"	(controller: "country", action: "delete", method: "DELETE")
		}

		group "/roles", {
			"/"		(controller: "roles", action: "index", method: "GET")
			"/$id"	(controller: "roles", action: "show", method: "GET")
			"/"		(controller: "roles", action: "save", method: "POST")
			"/$id"	(controller: "roles", action: "update", method: "PUT")
			"/$id"	(controller: "roles", action: "delete", method: "DELETE")
		}

		group "/videos", {
			"/"		(controller: "videos", action: "index", method: "GET")
			"/$id"	(controller: "videos", action: "show", method: "GET")
			"/"		(controller: "videos", action: "save", method: "POST")
			"/$id"	(controller: "videos", action: "update", method: "PUT")
			"/$id"	(controller: "videos", action: "delete", method: "DELETE")
		}

		group "/profile", {
			"/"				(controller: "profile", action: "index", method: "GET")
			"/"		(controller: "profile", action: "update", method: "PUT")
			"/photos/"		(controller: "profilePhotos", action: "index", method: "GET")
			"/photos/$id"	(controller: "profilePhotos", action: "show", method: "GET")
			"/photos/"		(controller: "profilePhotos", action: "save", method: "POST")
			"/photos/$id"	(controller: "profilePhotos", action: "update", method: "PUT")
			"/photos/$id"	(controller: "profilePhotos", action: "delete", method: "DELETE")
			"/bands/"		(controller: "profileBands", action: "index", method: "GET")
			"/bands/$id"	(controller: "profileBands", action: "show", method: "GET")
			"/bands/"		(controller: "profileBands", action: "save", method: "POST")
			"/bands/$id"	(controller: "profileBands", action: "update", method: "PUT")
			"/bands/$id"	(controller: "profileBands", action: "delete", method: "DELETE")
		}

		group "/users" , {
			"/register"			(controller: "users", action: "register", method: "post")
			"/resetPassword"	(controller: "users", action: "resetPassword", method: "post")
			"/confirmToken"		(controller: "users", action: "confirmToken", method: "post")
		}

		group "/tracks", {
			"/"				(controller: "tracks", action: "index", method: "GET")
			"/$id"			(controller: "tracks", action: "show", method: "GET")
			"/random"		(controller: "tracks", action: "random", method: "GET")
			"/"				(controller: "tracks", action: "save", method: "POST")
			"/$id"			(controller: "tracks", action: "update", method: "PUT")
			"/$id"			(controller: "tracks", action: "delete", method: "DELETE")
		}

		group "/photos", {
			"/"				(controller: "photos", action: "index", method: "GET")
			"/$id"			(controller: "photos", action: "show", method: "GET")
			"/random"		(controller: "photos", action: "random", method: "GET")
			"/"				(controller: "photos", action: "save", method: "POST")
			"/$id"			(controller: "photos", action: "update", method: "PUT")
			"/$id"			(controller: "photos", action: "delete", method: "DELETE")
		}
		
		group "/discs", {
			"/"		(controller: "discs", action: "index", method: "GET")
			"/$id"	(controller: "discs", action: "show", method: "GET")
			"/"		(controller: "discs", action: "save", method: "POST")
			"/$id"	(controller: "discs", action: "update", method: "PUT")
			"/$id"	(controller: "discs", action: "delete", method: "DELETE")
		}
		
		group "/bands", {
			"/"		(controller: "bands", action: "index", method: "GET")
			"/$id"	(controller: "bands", action: "show", method: "GET")
			"/"		(controller: "bands", action: "save", method: "POST")
			"/$id"	(controller: "bands", action: "update", method: "PUT")
			"/$id"	(controller: "bands", action: "delete", method: "DELETE")
		}
		
		group "/bars", {
			"/"		(controller: "bars", action: "index", method: "GET")
			"/$id"	(controller: "bars", action: "show", method: "GET")
			"/"		(controller: "bars", action: "save", method: "POST")
			"/$id"	(controller: "bars", action: "update", method: "PUT")
			"/$id"	(controller: "bars", action: "delete", method: "DELETE")
		}

		group "/artists", {
			"/"		(controller: "artists", action: "index", method: "GET")
			"/$id"	(controller: "artists", action: "show", method: "GET")
			"/"		(controller: "artists", action: "save", method: "POST")
			"/$id"	(controller: "artists", action: "update", method: "PUT")
			"/$id"	(controller: "artists", action: "delete", method: "DELETE")
		}
		
		group "/events", {
			"/"		(controller: "events", action: "index", method: "GET")
			"/$id"	(controller: "events", action: "show", method: "GET")
			"/"		(controller: "events", action: "save", method: "POST")
			"/$id"	(controller: "events", action: "update", method: "PUT")
			"/$id"	(controller: "events", action: "delete", method: "DELETE")
		}
		
		group "/postulates", {
			"/"		(controller: "postulates", action: "index", method: "GET")
			"/$id"	(controller: "postulates", action: "show", method: "GET")
			"/"		(controller: "postulates", action: "save", method: "POST")
			"/$id"	(controller: "postulates", action: "update", method: "PUT")
			"/$id"	(controller: "postulates", action: "delete", method: "DELETE")
		}

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
