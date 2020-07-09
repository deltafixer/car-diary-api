package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.api.RegularUserDTO;
import rs.ac.ni.pmf.web.model.entity.RegularUserEntity;

@Component
@RequiredArgsConstructor
public class RegularUserMapper {
	private final PasswordEncoder passwordEncoder;

	public RegularUserDTO toDto(final RegularUserEntity regularUserEntity) {
		return RegularUserDTO.builder().username(regularUserEntity.getUsername())
				.userType(regularUserEntity.getUserType()).firstName(regularUserEntity.getFirstName())
				.lastName(regularUserEntity.getLastName()).build();
	}

	public RegularUserEntity toEntity(final RegularUserDTO regularUserDto) {
		return RegularUserEntity.builder().username(regularUserDto.getUsername())
				.password(passwordEncoder.encode(regularUserDto.getPassword())).userType(regularUserDto.getUserType())
				.firstName(regularUserDto.getFirstName()).lastName(regularUserDto.getLastName()).build();
	}
}
