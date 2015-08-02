package com.musichub.util.amazon.s3

import grails.core.GrailsApplication
import grails.validation.ValidationException

import java.nio.file.Files;
import java.util.Map;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest.StandardMultipartFile;

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.amazonaws.services.s3.model.S3Object
import com.musichub.ApplicationContextHolder;
import com.musichub.Track;
import com.musichub.Video
import com.musichub.util.MimeTypeConstants;

class AmazonS3Uploader {
	static ApplicationContextHolder applicationContextHolder = ApplicationContextHolder.instance
	public static Map video(StandardMultipartFile file, Video video) throws ValidationException {

		if(!MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename)).equals('video/webm')) {
			video.errors.reject(
				'video.file.doesnotmatch',
				['file', 'class Video'] as Object[],
				'[Property [{0}] of class [{1}] contains an invalid format, only webm is supported.]'
			)
			throw new ValidationException('Video format is invalid, only webm is supported.', video.errors)
		}

		GrailsApplication ctx = applicationContextHolder.getGrailsApplication()

		AmazonS3Client s3Client=new AmazonS3Client(
			new BasicAWSCredentials(
				ctx.config.getProperty('amazon.credentials.access.key'),
				ctx.config.getProperty('amazon.credentials.access.secret')
			)
		)
		try {
			String newFilename = "videos/${UUID.randomUUID().toString()}.${FilenameUtils.getExtension(file.filename)}"

			AccessControlList access = new AccessControlList()
				.grantPermission(GroupGrantee.AllUsers, Permission.Read)
			
			ObjectMetadata metadata = new ObjectMetadata()
				.setContentType(
					MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename))
				)

			PutObjectRequest objectRequest = new PutObjectRequest(
					ctx.config.getProperty('amazon.credentials.bucket.name'),
					newFilename,
					file.getInputStream(),
					metadata)
			objectRequest.withMetadata(metadata)
			objectRequest.withAccessControlList(access)
			objectRequest.withCannedAcl(CannedAccessControlList.PublicRead)

			PutObjectResult result = s3Client.putObject(objectRequest)

			S3Object s3object = s3Client.getObject(
				new GetObjectRequest(
						ctx.config.getProperty('amazon.credentials.bucket.name'), 
						newFilename
					)
			)
			Map resultMetadata = [
				public_id: "${newFilename}",
				public_url: "https://${ctx.config.getProperty('amazon.credentials.bucket.name')}.s3.amazonaws.com/${newFilename}",
				format: "${MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename))}"
			]
			return resultMetadata
		} catch (AmazonServiceException exception) {
			System.out.println("Error AmazonServiceException Message:    " + exception.getMessage());
			return null
		} catch (AmazonClientException exception) {
			System.out.println("Error AmazonClientException Message:    " + exception.getMessage());
			return null
		}
	}

	public static Map track(StandardMultipartFile file, Track track) throws ValidationException {
		GrailsApplication ctx = applicationContextHolder.getGrailsApplication()
		
		AmazonS3Client s3Client=new AmazonS3Client(
			new BasicAWSCredentials(
				ctx.config.getProperty('amazon.credentials.access.key'),
				ctx.config.getProperty('amazon.credentials.access.secret')
			)
		)

		if(!(MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename)).equals('audio/mpeg') || MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename)).equals('application/ogg'))) {
			track.errors.reject(
				'track.file.doesnotmatch',
				['file', 'class Track'] as Object[],
				'[Property [{0}] of class [{1}] contains an invalid format, only webm is supported.]'
			)
			throw new ValidationException('Audio format is invalid, only mp3 and ogg is supported.', track.errors)
		}

		try {
			String newFilename = "audios/${UUID.randomUUID().toString()}.${FilenameUtils.getExtension(file.filename)}"

			AccessControlList access = new AccessControlList()
				.grantPermission(GroupGrantee.AllUsers, Permission.Read)
			
			ObjectMetadata metadata = new ObjectMetadata()
				.setContentType(
					MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename))
				)

			PutObjectRequest objectRequest = new PutObjectRequest(
					ctx.config.getProperty('amazon.credentials.bucket.name'),
					newFilename,
					file.getInputStream(),
					metadata)
			objectRequest.withMetadata(metadata)
			objectRequest.withAccessControlList(access)
			objectRequest.withCannedAcl(CannedAccessControlList.PublicRead)

			PutObjectResult result = s3Client.putObject(objectRequest)

			S3Object s3object = s3Client.getObject(
				new GetObjectRequest(
						ctx.config.getProperty('amazon.credentials.bucket.name'),
						newFilename
					)
			)
			Map resultMetadata = [
				public_id: "${newFilename}",
				public_url: "https://${ctx.config.getProperty('amazon.credentials.bucket.name')}.s3.amazonaws.com/${newFilename}",
				format: "${MimeTypeConstants.getMimeType(FilenameUtils.getExtension(file.filename))}"
			]
			return resultMetadata
		} catch (AmazonServiceException exception) {
			System.out.println("Error AmazonServiceException Message:    " + exception.getMessage());
			return null
		} catch (AmazonClientException exception) {
			System.out.println("Error AmazonClientException Message:    " + exception.getMessage());
			return null
		}
	}
}
