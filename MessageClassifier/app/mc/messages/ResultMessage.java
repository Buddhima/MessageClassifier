package mc.messages;

/**
 * Result After Processing
 *
 */
public class ResultMessage {

	private String result;
	private String service;
	
	public ResultMessage(String service) {
		this.service = service;
		
		// Create null result message
	}

	public ResultMessage(String service, String result) {
		this.service = service;
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	public String getService() {
		return service;
	}
}
