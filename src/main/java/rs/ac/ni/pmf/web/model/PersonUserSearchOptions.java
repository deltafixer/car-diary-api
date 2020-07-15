package rs.ac.ni.pmf.web.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonUserSearchOptions {

	@ApiModelProperty(value = "Person's first name.")
	private String firstName;
	@ApiModelProperty(value = "Person's last name.")
	private String lastName;
	@ApiModelProperty(value = "Person with at least this much vehicles.")
	private Integer minVehicleCount;
	@ApiModelProperty(value = "Person with as much as this count of vehicles.")
	private Integer maxVehicleCount;
	@ApiModelProperty(value = "Page number.")
	private Integer page;
	@ApiModelProperty(value = "Page size.")
	private Integer size;

}
