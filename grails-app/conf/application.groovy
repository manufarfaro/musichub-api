cloudinary {
	cloud{
		name = "musichub"		
	}
	api {
		key = "changeme"
		secret = "changeme"
	}
}

amazon {
	credentials {
		bucket {
			name = "static-musichub"
		}
		access {
			key = "changeme"
			secret = "changeme" 
		}
	}
}

google {
	secrets {
		path = "credentials/musichub-api-google.json";
	}
	credentials {
		path = System.getProperty('catalina.base') ?
        "${System.getProperty('catalina.base')}/data/oauth-credentials" :
        "${System.getProperty('user.home')}/.oauth-credentials";
	}
}
