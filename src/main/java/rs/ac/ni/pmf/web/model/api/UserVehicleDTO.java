package rs.ac.ni.pmf.web.model.api;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ApiModel(description = "Data transfer object used to help with a many to many relation.")
public class UserVehicleDTO {

	// COMMENT: wish I could use Optional here...
	private String username;
	private VehicleDTO vehicle;

}
