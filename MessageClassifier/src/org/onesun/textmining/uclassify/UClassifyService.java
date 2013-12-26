/*
 **
	Copyright 2010 Udaya Kumar (Udy)

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 **	
 */
package org.onesun.textmining.uclassify;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.onesun.utils.http.Request;
import org.onesun.utils.http.Response;
import org.onesun.utils.http.HTTPMethod;

public class UClassifyService {
	private final static Logger logger = Logger.getLogger(UClassifyService.class);
	
	private static String uClassifyReadAccessKey = null;
	
	public static void setUClassifyReadAccessKey(String uClassifyReadAccessKey){
		UClassifyService.uClassifyReadAccessKey = uClassifyReadAccessKey;
	}
	
	private String text = null;
	private MediaType mediaType = MediaType.XML;
	private ServiceType service = null;
	private ResultHandler resultHandler = null;

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	public UClassifyService(String text, ServiceType service, ResultHandler resultHandler) {
		super();
		
		this.text = text;
		this.service = service;
		
		this.resultHandler = resultHandler;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String buildServiceURL(){
		try {
			String encodedText = URLEncoder.encode(text, ("UTF-8"));
			
			return ( 
				service.getUrl() 
				+ "/" 
				+ service.getClassifier() 
				+ "/ClassifyText?readkey=" 
				+ uClassifyReadAccessKey 
				+ "&text=" 
				+ encodedText 
				+ "&output=" 
				+ mediaType.getType()
			);
			
		}catch (UnsupportedEncodingException e){
			logger.info("Charset UTF-8 unsupported");
		}
		finally {
		}
		
		return null;
	}
	
	public void process() throws Exception {
		if(uClassifyReadAccessKey == null || uClassifyReadAccessKey.length() <= 0){
			throw new Exception("uClassify API Read Key undefined");
		}
		
		String url = buildServiceURL();

		Request request = new Request(HTTPMethod.POST, url);
		
		Response response = request.send();
		
		String responseBody = response.getBody();
		
		ResultProcessor resultProcessor = new DefaultResultProcessor(responseBody);
		
		if(resultHandler != null){
			resultHandler.process(service, resultProcessor.getResults());
		}
	}
}
