package com.spring.project.Exceptions.Model.Response;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
public class ErrorResponse {

    private Timestamp timestamp;
    private int status;
    private String errorReason;
    private String message;
    private String path;
    
    private String response;
    
	public ErrorResponse(Timestamp timestamp, int status, String errorReason, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.errorReason = errorReason;
		this.message = message;
		this.path = path;
	}

	public String getResponse() {
		
		response = "{\n\t\"timestamp\": " + timestamp + ",";
		response += "\n\t\"status\": " + status + ",";
		response += "\n\t\"error\": " + errorReason + ",";
		response += "\n\t\"message\": " + message + ",";
		response += "\n\t\"path\": " + path;
		response += "\n}";
		
		return response;
	}
    
}
