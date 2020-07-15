package rs.ac.ni.pmf.web.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ApiModel(description = "Data transfer object representing a service user.")
public class ServiceUserDTO extends UserDTO {

	@ApiModelProperty(value = "Service's name.", required = true, example = "Mile Limar")
	private String name;
	@ApiModelProperty(value = "Type of the service (authorized or not).", required = true, example = "AUTHORIZED")
	private ServiceType serviceType;

}
