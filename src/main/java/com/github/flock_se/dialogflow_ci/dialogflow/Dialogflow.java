package com.github.flock_se.dialogflow_ci.dialogflow;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.zip.DataFormatException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.flock_se.dialogflow_ci.dialogflow.helper.RequestHelper;
import com.github.flock_se.dialogflow_ci.dialogflow.helper.ResponseHelper;

public class Dialogflow {
	
	private String lastResponse;
	private RequestHelper requestHelper;
	private ResponseHelper responseHelper;

	public Dialogflow(String projectId, String token) {
		this.requestHelper = new RequestHelper(projectId, token);
		this.responseHelper = new ResponseHelper();
	}

	public void say(String sentence) throws JsonProcessingException {
		this.lastResponse = requestHelper.query(sentence);
		System.out.println(lastResponse);
	}

	public String getIntent() throws JsonParseException, JsonMappingException, IOException {
		// TODO: raise a nice exception
		if (lastResponse == null)
			Assert.fail("No response! (getIntent)");
		
		return responseHelper.getIntent(lastResponse);
	}	
	
	public void download(String dir) throws DataFormatException, IOException {
		String response = requestHelper.download();
		byte[] compressedData = responseHelper.getZipDataFrom(response);
		
		File file = new File(dir);
		file.mkdirs();
		
	    ZipInputStream zi = null;
	    try {
	        zi = new ZipInputStream(new ByteArrayInputStream(compressedData));
	        ZipEntry zipEntry = null;
	        while ((zipEntry = zi.getNextEntry()) != null) {
	        	Path path = Paths.get(file.getAbsolutePath(), zipEntry.getName().replaceAll(" ", "\\ "));
	        	Files.createDirectories(path.getParent());
	        	Files.copy(zi, path, StandardCopyOption.REPLACE_EXISTING);
	        }
	    } finally {
	        if (zi != null) {
	            zi.close();
	        }
	    }
	}
	
	public void upload(String dir) {
		
	}
}
