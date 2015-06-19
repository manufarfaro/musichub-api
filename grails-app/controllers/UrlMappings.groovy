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
		}

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
