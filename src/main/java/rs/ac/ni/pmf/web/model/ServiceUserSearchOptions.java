package rs.ac.ni.pmf.web.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@Value
@Builder
public class ServiceUserSearchOptions {

	@ApiModelProperty(value = "Service's name.")
	private String name;
	@ApiModelProperty(value = "Type of service (authorized or not).")
	private ServiceType serviceType;
	@ApiModelProperty(value = "Minimum count of services done by this service.")
	private Integer minServicesCount;
	@ApiModelProperty(value = "Minimum count of services done by this service.")
	private Integer maxServicesCount;
	@ApiModelProperty(value = "Page number.")
	private Integer page;
	@ApiModelProperty(value = "Page size.")
	private Integer size;

}
