package rs.ac.ni.pmf.web.model.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.pmf.web.model.entity.UserEnums.UserType;

@Data
@SuperBuilder
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NonFinal
public abstract class UserDTO {
	protected Integer id;
	private String username;
	private UserType userType;
}
