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
package org.onesun.utils.http;

import static org.onesun.utils.encoders.URL.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Request {

	private static final String CONTENT_LENGTH = "Content-Length";

	private String url;
	private HTTPMethod method;
	private Map<String, String> bodyParams;
	private Map<String, String> headers;
	private String payload = null;

	public Request(HTTPMethod method, String url) {
		this.method = method;
		this.url = url;
		this.bodyParams = new HashMap<String, String>();
		this.headers = new HashMap<String, String>();
	}

	public Response send() {
		try {
			return doSend();
		} catch (IOException ioe) {
			throw new RuntimeException("Problems while creating connection",
					ioe);
		}
	}

	synchronized private Response doSend() throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod(this.method.name());
		addHeaders(connection);
		if (method.equals(HTTPMethod.PUT) || method.equals(HTTPMethod.POST)) {
			addBody(connection, getBodyContents());
		}
		return new Response(connection);
	}

	void addHeaders(HttpURLConnection conn) {
		for (String key : headers.keySet())
			conn.setRequestProperty(key, headers.get(key));
	}

	void addBody(HttpURLConnection conn, String content) throws IOException {
		conn.setRequestProperty(CONTENT_LENGTH, String.valueOf(content
				.getBytes().length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(content.getBytes());
	}

	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public void addBodyParameter(String key, String value) {
		this.bodyParams.put(key, value);
	}

	public void addPayload(String payload) {
		this.payload = payload;
	}

	public Set<Map.Entry<String, String>> getQueryStringParams() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			String query = new URL(url).getQuery();
			if (query != null) {
				for (String param : query.split("&")) {
					String pair[] = param.split("=");
					params.put(pair[0], pair[1]);
				}
			}
			return params.entrySet();
		} catch (MalformedURLException mue) {
			throw new RuntimeException("Malformed URL", mue);
		}
	}

	public Set<Map.Entry<String, String>> getBodyParams() {
		return bodyParams.entrySet();
	}

	public String getUrl() {
		return url;
	}

	public String getSanitizedUrl() {
		return url.replaceAll("\\?.*", "").replace("\\:\\d{4}", "");
	}

	public String getBodyContents() {
		return (payload != null) ? payload : queryString(bodyParams)
				.replaceFirst("\\?", "");
	}

	public HTTPMethod getMethod() {
		return method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}
}