package rs.ac.ni.pmf.web.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonUserSearchOptions {

	private String firstName;
	private String lastName;
	private Integer minVehicleCount;
	private Integer maxVehicleCount;
	private Integer page;
	private Integer size;

}
