package rs.ac.ni.pmf.web.exception;

import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ErrorCode;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;

@Value
@Builder
public class ErrorInfo {
	private ErrorCode errorCode;
	private ResourceType resourceType;
	private String message;
}
