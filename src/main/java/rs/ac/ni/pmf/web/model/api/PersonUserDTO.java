package rs.ac.ni.pmf.web.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ApiModel(description = "Data transfer object representing a person user.")
public class PersonUserDTO extends UserDTO {

	@ApiModelProperty(value = "First name of the person.", required = true)
	private String firstName;
	@ApiModelProperty(value = "Last name of the person.", required = true)
	private String lastName;

}
