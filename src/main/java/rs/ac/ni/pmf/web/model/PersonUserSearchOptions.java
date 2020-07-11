package rs.ac.ni.pmf.web.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonUserSearchOptions {

	private String firstNameFilter;
	private String lastNameFilter;
	private Integer minVehicleCount;
	private Integer maxVehicleCount;
	private Integer page;
	private Integer size;

}
