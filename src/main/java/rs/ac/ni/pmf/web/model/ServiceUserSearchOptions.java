package rs.ac.ni.pmf.web.model;

import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@Value
@Builder
public class ServiceUserSearchOptions {

	private String name;
	private ServiceType serviceType;
	private Integer minServicesCount;
	private Integer maxServicesCount;
	private Integer page;
	private Integer size;

}
