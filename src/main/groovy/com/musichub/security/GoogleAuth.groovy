package com.musichub.security

import java.security.KeyStore;
import java.security.PrivateKey;

import grails.core.GrailsApplication;
import groovy.json.JsonSlurper;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.grails.io.support.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.auth.oauth2.StoredCredential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.store.DataStore
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.drive.DriveScopes
import com.google.api.services.youtube.YouTubeScopes
import com.musichub.ApplicationContextHolder

class GoogleAuth {

	static ApplicationContextHolder applicationContextHolder = ApplicationContextHolder.instance

	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance()
	public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport()
	private static final List<String> SCOPES = [YouTubeScopes.YOUTUBE, DriveScopes.DRIVE, DriveScopes.DRIVE_FILE]
	
	public static Credential authorize() throws IOException {

		GrailsApplication ctx = applicationContextHolder.getGrailsApplication()

		GoogleCredential credential = new GoogleCredential().fromStream(
			GoogleAuth.class.classLoader.getResourceAsStream(ctx.config.getProperty('google.secrets.path')),
			this.HTTP_TRANSPORT,
			this.JSON_FACTORY
		)

		return credential.createScoped(this.SCOPES)
	}
}
