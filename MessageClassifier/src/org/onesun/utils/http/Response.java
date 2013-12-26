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

import java.io.*;
import java.net.*;
import java.util.*;

public class Response {

	private static final String ENCODING = "UTF-8";
	private static final String EMPTY = "";

	private int code;
	private String body;
	private InputStream stream;
	private Map<String, String> headers;

	Response(HttpURLConnection connection) throws IOException {
		try {
			connection.connect();
			code = connection.getResponseCode();
			headers = parseHeaders(connection);
			stream = wasSuccessful() ? connection.getInputStream() : connection
					.getErrorStream();
		} catch (UnknownHostException e) {
			code = 404;
			body = EMPTY;
		}
	}

	private String parseBodyContents() {
		try {
			final char[] buffer = new char[0x10000];
			StringBuilder out = new StringBuilder();
			Reader in = new InputStreamReader(stream, ENCODING);
			int read;
			do {
				read = in.read(buffer, 0, buffer.length);
				if (read > 0) {
					out.append(buffer, 0, read);
				}
			} while (read >= 0);
			in.close();
			body = out.toString();
			return body;
		} catch (IOException ioe) {
			throw new RuntimeException("Error while reading response body", ioe);
		}
	}

	private Map<String, String> parseHeaders(HttpURLConnection conn) {
		Map<String, String> headers = new HashMap<String, String>();
		for (String key : conn.getHeaderFields().keySet()) {
			headers.put(key, conn.getHeaderFields().get(key).get(0));
		}
		return headers;
	}

	private boolean wasSuccessful() {
		return code >= 200 && code < 400;
	}

	public String getBody() {
		return body != null ? body : parseBodyContents();
	}

	public InputStream getStream() {
		return stream;
	}

	public int getCode() {
		return code;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getHeader(String name) {
		return headers.get(name);
	}
}