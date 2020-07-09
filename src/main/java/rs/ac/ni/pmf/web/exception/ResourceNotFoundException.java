package rs.ac.ni.pmf.web.exception;

import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;

public class ResourceNotFoundException extends ResourceException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final ResourceType resourceType, final String message) {
		super(resourceType, message);
	}
}
