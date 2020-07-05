package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.RegularUserDTO;
import rs.ac.ni.pmf.web.model.entity.RegularUserEntity;

@Component
public class RegularUserMapper {

	public RegularUserDTO toDto(final RegularUserEntity regularUserEntity) {
		return RegularUserDTO.builder().username(regularUserEntity.getUsername())
				.userType(regularUserEntity.getUserType()).firstName(regularUserEntity.getFirstName())
				.lastName(regularUserEntity.getLastName()).build();
	}

	public RegularUserEntity toEntity(final RegularUserDTO regularUserDto) {
		return RegularUserEntity.builder().username(regularUserDto.getUsername()).userType(regularUserDto.getUserType())
				.firstName(regularUserDto.getFirstName()).lastName(regularUserDto.getLastName()).build();
	}
}
