package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.entity.PersonUserEntity;

@Component
@RequiredArgsConstructor
public class PersonUserMapper {

	private final PasswordEncoder passwordEncoder;

	public PersonUserDTO toDto(final PersonUserEntity personUserEntity) {
		return PersonUserDTO.builder().username(personUserEntity.getUsername()).userType(personUserEntity.getUserType())
				.firstName(personUserEntity.getFirstName()).lastName(personUserEntity.getLastName()).build();
	}

	public PersonUserEntity toEntity(final PersonUserDTO personUserDto) {
		return PersonUserEntity.builder().username(personUserDto.getUsername())
				.password(passwordEncoder.encode(personUserDto.getPassword())).userType(personUserDto.getUserType())
				.firstName(personUserDto.getFirstName()).lastName(personUserDto.getLastName()).build();
	}

}
