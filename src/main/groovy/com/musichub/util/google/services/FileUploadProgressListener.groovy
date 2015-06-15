package com.musichub.util.google.services

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.api.client.googleapis.media.MediaHttpUploader
import com.google.api.client.googleapis.media.MediaHttpUploader.UploadState;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener

class FileUploadProgressListener implements MediaHttpUploaderProgressListener {

	private final Log logger = LogFactory.getLog(getClass())

	public void progressChanged(MediaHttpUploader uploader) throws IOException {
		switch (uploader.getUploadState()) {
		  case UploadState.INITIATION_STARTED:
			logger.debug("Initiation Started");
			break;
		  case UploadState.INITIATION_COMPLETE:
			logger.debug("Initiation Completed");
			break;
		  case UploadState.MEDIA_IN_PROGRESS:
			logger.debug("Upload percentage: " + uploader.getProgress());
			break;
		  case UploadState.MEDIA_COMPLETE:
			logger.debug("Upload Completed!");
			break;
  
		  case UploadState.NOT_STARTED :
			  logger.debug("Not Started!");
			  break;
		}
	  }
}
