/* 
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
*/
package org.onesun.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {
	public static String getValue(Element item, String tag){
		String textValue = null;
		
		NodeList nodes = item.getElementsByTagName(tag);
		if(nodes != null && nodes.getLength() > 0){
			Element element = (Element)nodes.item(0);
			
			Node node = element.getFirstChild();
			
			if(node != null) {
				textValue = node.getNodeValue();
			}
		}
		
		return textValue;
	}
	
	public static Element getElement(Element item, String tag){
		NodeList nodes = item.getElementsByTagName(tag);
		if(nodes != null && nodes.getLength() > 0){
			
			Element element = (Element)nodes.item(0);
			
			String nodeName = null; 
			if(element != null) {
				nodeName = element.getNodeName();
			}
			
			if(nodeName != null && nodeName.compareTo(tag) == 0){
				return element;
			}
		}
		
		return null;
	}
}
