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

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
