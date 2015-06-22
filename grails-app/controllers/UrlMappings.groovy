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

		group "/videos", {
			"/"		(controller: "videos", action: "index", method: "GET")
			"/$id"	(controller: "videos", action: "show", method: "GET")
			"/"		(controller: "videos", action: "save", method: "POST")
			"/$id"	(controller: "videos", action: "update", method: "PUT")
			"/$id"	(controller: "videos", action: "delete", method: "DELETE")
		}
		
		group "/profile", {
			"/"				(controller: "profile", action: "index", method: "GET")
			"/$id"			(controller: "profile", action: "update", method: "PUT")
			"/photo/"		(controller: "profile_photos", action: "index", method: "GET")
			"/photo/id"		(controller: "profile_photos", action: "show", method: "GET")
			"/photo/"		(controller: "profile_photos", action: "save", method: "POST")
			"/photo/$id"	(controller: "profile_photos", action: "update", method: "PUT")
			"/photo/$id"	(controller: "profile_photos", action: "delete", method: "DELETE")

		}

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
