class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
		group "/quotes", {
			"/"			(controller: "quotes", action: "index", method: "GET")
			"/$id"		(controller: "quotes", action: "show", method: "GET")
			"/random"	(controller: "quotes", action: "random", method: "GET")
			"/"			(controller: "quotes", action: "save", method: "POST")
			"/$id"		(controller: "quotes", action: "update", method: "PUT")
			"/$id"		(controller: "quotes", action: "delete", method: "DELETE")
		}

		group "/countries", {
			"/"		(controller: "countries", action: "index", method: "GET")
			"/$id"	(controller: "countries", action: "show", method: "GET")
			"/"		(controller: "countries", action: "save", method: "POST")
			"/$id"	(controller: "countries", action: "update", method: "PUT")
			"/$id"	(controller: "countries", action: "delete", method: "DELETE")
		}

		group "/roles", {
			"/"		(controller: "roles", action: "index", method: "GET")
			"/$id"	(controller: "roles", action: "show", method: "GET")
			"/"		(controller: "roles", action: "save", method: "POST")
			"/$id"	(controller: "roles", action: "update", method: "PUT")
			"/$id"	(controller: "roles", action: "delete", method: "DELETE")
		}

		group "/videos", {
			"/"			(controller: "videos", action: "index", method: "GET")
			"/$id"		(controller: "videos", action: "show", method: "GET")
			"/random"	(controller: "videos", action: "random", method: "GET")
			"/"			(controller: "videos", action: "save", method: "POST")
			"/$id"		(controller: "videos", action: "update", method: "PUT")
			"/$id"		(controller: "videos", action: "delete", method: "DELETE")
		}

		group "/profile", {
			"/"									(controller: "profile", action: "index", method: "GET")
			"/"									(controller: "profile", action: "update", method: "PUT")
			"/photos"							(controller: "profilePhotos", action: "index", method: "GET")
			"/bands"							(controller: "profileBands", action: "index", method: "GET")
			"/discs"							(controller: "profileDiscs", action: "index", method: "GET")
			"/videos"							(controller: "profileVideos", action: "index", method: "GET")
			"/postulated"						(controller: "profilePostulates", action: "indexPostulates", method: "GET")
			"/postulations"						(controller: "profilePostulates", action: "indexPostulations", method: "GET")
		}

		group "/users" , {
			"/register/"				(controller: "users", action: "register", method: "POST")
			"/resetPassword/$id"		(controller: "users", action: "resetPassword", method: "POST")
			"/confirmToken/$token"		(controller: "users", action: "confirmResetPasswordToken", method: "POST")
			"/changePassword/"		(controller: "users", action: "changePassword", method: "POST")
		}

		group "/tracks", {
			"/"				(controller: "tracks", action: "index", method: "GET")
			"/$id"			(controller: "tracks", action: "show", method: "GET")
			"/random"		(controller: "tracks", action: "random", method: "GET")
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
			"/"					(controller: "discs", action: "index", method: "GET")
			"/$id"				(controller: "discs", action: "show", method: "GET")
			"/"					(controller: "discs", action: "save", method: "POST")
			"/$id"				(controller: "discs", action: "update", method: "PUT")
			"/$id"				(controller: "discs", action: "delete", method: "DELETE")
			"/$disc_id/tracks"	(controller: "tracks", action: "save", method: "POST")
		}
		
		group "/bands", {
			"/"													(controller: "bands", action: "index", method: "GET")
			"/$id"												(controller: "bands", action: "show", method: "GET")
			"/"													(controller: "bands", action: "save", method: "POST")
			"/$id"												(controller: "bands", action: "update", method: "PUT")
			"/$id"												(controller: "bands", action: "delete", method: "DELETE")
			"/$band_id/photos/"									(controller: "photos", action: "save", method: "POST")
			"/$band_id/photos/$photo_id"						(controller: "photos", action: "update", method: "PUT")
			"/$band_id/photos/$photo_id"						(controller: "photos", action: "delete", method: "DELETE")
			"/$band_id/videos/"									(controller: "videos", action: "save", method: "POST")
			"/$band_id/videos/$video_id"						(controller: "videos", action: "update", method: "PUT")
			"/$band_id/videos/$video_id"						(controller: "videos", action: "delete", method: "DELETE")
			"/$band_id/discs/"									(controller: "discs", action: "save", method: "POST")
			"/$band_id/discs/$disc_id"							(controller: "discs", action: "update", method: "PUT")
			"/$band_id/discs/$disc_id"							(controller: "discs", action: "delete", method: "DELETE")
			"/$band_id/artists/$artist_id/add"					(controller: "bands", action: "addArtist", method: "POST")
			"/$band_id/postulates/$postulate_id/postulate"		(controller: "postulates", action: "postulate", method: "POST")
			
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
			"/"							(controller: "postulates", action: "index", method: "GET")
			"/$id"						(controller: "postulates", action: "show", method: "GET")
			"/"							(controller: "postulates", action: "save", method: "POST")
			"/$id"						(controller: "postulates", action: "update", method: "PUT")
			"/$id"						(controller: "postulates", action: "delete", method: "DELETE")
			"/$postulate_id/postulate"	(controller: "postulates", action: "postulate", method: "POST")
		}

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
