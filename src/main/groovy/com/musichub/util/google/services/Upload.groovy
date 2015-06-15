package com.musichub.util.google.services

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.json.GoogleJsonError
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.Permission;
import com.google.api.services.drive.model.User
import com.google.api.client.http.FileContent
import com.google.api.client.http.HttpResponse
import com.google.api.client.http.HttpResponseException
import com.google.api.client.http.InputStreamContent
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.ParentReference
import com.google.api.services.drive.model.User.Picture;
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.Video
import com.google.api.services.youtube.model.VideoSnippet
import com.google.api.services.youtube.model.VideoStatus
import com.musichub.security.GoogleAuth

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch

class Upload {

	private final Log logger = LogFactory.getLog(getClass())

	protected Upload(){}
	
	private static Upload instance = null
	
	public static Upload getInstance() {
		if(instance == null) {
			instance = new Upload();
		 }
		 return instance;
	}
	
	public String toDrive(byte[] file, String parentId) {
		try {
			Credential googleCredentials = GoogleAuth.authorize()

			Drive driveService = new Drive.Builder(GoogleAuth.HTTP_TRANSPORT, GoogleAuth.JSON_FACTORY, googleCredentials)
				.setApplicationName("musichub-api")
				.build()
	
			MagicMatch fileMagicMatch = Magic.getMagicMatch(file)
	
			InputStreamContent mediaContent = new InputStreamContent(
				fileMagicMatch.getMimeType(),
				new BufferedInputStream(
						new ByteArrayInputStream(file)
				)
			)
			mediaContent.setLength(file.size())
			mediaContent.setType(fileMagicMatch.getMimeType())
			
	
			logger.debug("file: ${mediaContent}")
			File fileMetadata = new File()
				.setTitle("${UUID.randomUUID().toString()}.${fileMagicMatch.getExtension()}")
				.setShared(true)
				.setAppDataContents(true)
				.setMimeType(fileMagicMatch.getMimeType())
				.setFileExtension(fileMagicMatch.getExtension())
				.setFileSize(mediaContent.length)
				.setPermissions([
					new Permission()
						.setType("anyone")
						.setRole("reader")
				])
	
			if (parentId != null && parentId.length() > 0) {
				fileMetadata.setParents([
					new ParentReference().setId(parentId)
				])
			}

			Drive.Files.Insert insertedFile = driveService.files().insert(fileMetadata, mediaContent).set("uploadType", "multipart")

			if (logger.debugEnabled){
				MediaHttpUploader uploader = insertedFile.getMediaHttpUploader();
				uploader.setDirectUploadEnabled(false);
				uploader.setProgressListener(new FileUploadProgressListener());
			}
	
			File uploadedFile = insertedFile.execute()
	
			logger.debug("Uploaded File Response: ${uploadedFile.toPrettyString()}")
	
			return uploadedFile.getId()
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			logger.debug('Error code: ' + error.getCode());
			logger.debug('Error message: ' + error.getMessage());
			return false
		} catch (HttpResponseException e) {
	    	logger.debug('HTTP Status code: ' + e.getStatusCode());
			logger.debug('HTTP Reason: ' + e.getMessage());
			return false
		} catch (IOException exception) {
			logger.debug("Error: ${exception.getMessage()}")
			return false
		}
	}
	public static String toYouTube(byte[] file, String name) {
		Credential googleCredentials = GoogleAuth.authorize()
		
		MagicMatch fileMagicMatch = Magic.getMagicMatch(file)
		
		InputStreamContent mediaContent = new InputStreamContent(
			fileMagicMatch.getMimeType(),
			new BufferedInputStream(
					new ByteArrayInputStream(file)
			)
		)
		mediaContent.setLength(fileMagicMatch.getLength())
		
		YouTube youtube = new YouTube.Builder(
			GoogleAuth.HTTP_TRANSPORT,
			GoogleAuth.JSON_FACTORY,
			googleCredentials
		).setApplicationName("musichub-api")
		.build();

		Video videoObjectDefiningMetadata = new Video()

		VideoStatus status = new VideoStatus()
		status.setPrivacyStatus("unlisted")
		videoObjectDefiningMetadata.setStatus(status)

		VideoSnippet snippet = new VideoSnippet()
		snippet.setTitle(name)

		videoObjectDefiningMetadata.setSnippet(snippet)

		YouTube.Videos.Insert videoInsert = youtube.videos()
			.insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent)

		MediaHttpUploader uploader = videoInsert.getMediaHttpUploader()

		uploader.setDirectUploadEnabled(false)

		try {
			Video returnedVideo = videoInsert.execute();
			return returnedVideo.getId()
		} catch(Exception exception) {
			return false
		}
	}

}
