package rs.ac.ni.pmf.web.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.pmf.web.model.entity.UserEnums.Role;
import rs.ac.ni.pmf.web.model.entity.UserEnums.UserType;

@Data
@SuperBuilder
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NonFinal
@ApiModel(description = "Data transfer object representing a user (ABSTRACT).")
public abstract class UserDTO {

	@ApiModelProperty(value = "User's username.", required = true, example = "andrijacvetkovic")
	private String username;
	// COMMENT: PROFESSIONAL ENCRYPTION
	@ApiModelProperty(value = "User's password.", required = true, example = "*******")
	private String password;
	@ApiModelProperty(value = "User type.", required = true, example = "PERSON")
	private UserType userType;
	@ApiModelProperty(value = "User role.", required = true, example = "ADMIN")
	private Role role;

}
