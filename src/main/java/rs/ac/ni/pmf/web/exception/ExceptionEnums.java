package rs.ac.ni.pmf.web.exception;

public class ExceptionEnums {
	public enum ErrorCode {
		NOT_FOUND, DUPLICATE, AUTHENTICATION_FAILED, UNAUTHORIZED, BAD_REQUEST
	}
	public enum ResourceType {
		PERSON_USER, SERVICE_USER, VEHICLE, SALE_LISTING, VEHICLE_SPECIFICATION, VEHICLE_SERVICE, ACCESS, SENT_RESOURCE
	}
}
