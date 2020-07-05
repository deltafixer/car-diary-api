package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.ServiceUserDTO;
import rs.ac.ni.pmf.web.model.entity.ServiceUserEntity;

@Component
public class ServiceUserMapper {

	public ServiceUserDTO toDto(final ServiceUserEntity serviceUserEntity) {
		return ServiceUserDTO.builder().username(serviceUserEntity.getUsername())
				.userType(serviceUserEntity.getUserType()).serviceType(serviceUserEntity.getServiceType()).build();
	}

	public ServiceUserEntity toEntity(final ServiceUserDTO serviceUserDto) {
		return ServiceUserEntity.builder().username(serviceUserDto.getUsername()).userType(serviceUserDto.getUserType())
				.serviceType(serviceUserDto.getServiceType()).build();
	}
}
