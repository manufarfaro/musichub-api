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

//		group "/roles", {
//			"/"		(controller: "roles", action: "index", method: "GET")
//			"/$id"	(controller: "roles", action: "show", method: "GET")
//			"/"		(controller: "roles", action: "save", method: "POST")
//			"/$id"	(controller: "roles", action: "update", method: "PUT")
//			"/$id"	(controller: "roles", action: "delete", method: "DELETE")
//		}

		group "/videos", {
			"/"		(controller: "videos", action: "index", method: "GET")
			"/$id"	(controller: "videos", action: "show", method: "GET")
			"/"		(controller: "videos", action: "save", method: "POST")
			"/$id"	(controller: "videos", action: "update", method: "PUT")
			"/$id"	(controller: "videos", action: "delete", method: "DELETE")
		}

		group "/profile", {
			"/"				(controller: "profile", action: "index", method: "GET")
			"/"				(controller: "profile", action: "update", method: "PUT")
			"/photo/"		(controller: "profilePhotos", action: "index", method: "GET")
			"/photo/$id"	(controller: "profilePhotos", action: "show", method: "GET")
			"/photo/"		(controller: "profilePhotos", action: "save", method: "POST")
			"/photo/$id"	(controller: "profilePhotos", action: "update", method: "PUT")
			"/photo/$id"	(controller: "profilePhotos", action: "delete", method: "DELETE")

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
		
		group "/disc", {
			"/"		(controller: "disc", action: "index", method: "GET")
			"/$id"	(controller: "disc", action: "show", method: "GET")
			"/"		(controller: "disc", action: "save", method: "POST")
			"/$id"	(controller: "disc", action: "update", method: "PUT")
			"/$id"	(controller: "disc", action: "delete", method: "DELETE")
		}
		
		group "/band", {
			"/"		(controller: "band", action: "index", method: "GET")
			"/$id"	(controller: "band", action: "show", method: "GET")
			"/"		(controller: "band", action: "save", method: "POST")
			"/$id"	(controller: "band", action: "update", method: "PUT")
			"/$id"	(controller: "band", action: "delete", method: "DELETE")
		}
		
		group "/bar", {
			"/"		(controller: "bar", action: "index", method: "GET")
			"/$id"	(controller: "bar", action: "show", method: "GET")
			"/"		(controller: "bar", action: "save", method: "POST")
			"/$id"	(controller: "bar", action: "update", method: "PUT")
			"/$id"	(controller: "bar", action: "delete", method: "DELETE")
		}

		group "/artist", {
			"/"		(controller: "artist", action: "index", method: "GET")
			"/$id"	(controller: "artist", action: "show", method: "GET")
			"/"		(controller: "artist", action: "save", method: "POST")
			"/$id"	(controller: "artist", action: "update", method: "PUT")
			"/$id"	(controller: "artist", action: "delete", method: "DELETE")
		}
		
		group "/event", {
			"/"		(controller: "event", action: "index", method: "GET")
			"/$id"	(controller: "event", action: "show", method: "GET")
			"/"		(controller: "event", action: "save", method: "POST")
			"/$id"	(controller: "event", action: "update", method: "PUT")
			"/$id"	(controller: "event", action: "delete", method: "DELETE")
		}
		
		group "/postulate", {
			"/"		(controller: "postulate", action: "index", method: "GET")
			"/$id"	(controller: "postulate", action: "show", method: "GET")
			"/"		(controller: "postulate", action: "save", method: "POST")
			"/$id"	(controller: "postulate", action: "update", method: "PUT")
			"/$id"	(controller: "postulate", action: "delete", method: "DELETE")
		}

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
