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

import java.util.Map;

public abstract class AbstractResultProcessor implements ResultProcessor {
	public AbstractResultProcessor(String resultText){
		this.resultText = resultText;
	}
	
	protected String resultText = null;
	
	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	protected abstract void processResultText();
	
	protected Map<String, Double> results = null;

	@Override
	public Map<String, Double> getResults() {
		processResultText();
		
		return results;
	}
}
