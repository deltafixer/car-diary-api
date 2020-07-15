package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.ServiceUserRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ServiceUserSearchOptions;
import rs.ac.ni.pmf.web.model.api.ServiceUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;
import rs.ac.ni.pmf.web.service.ServiceUserService;

@RestController
@RequiredArgsConstructor
public class ServiceUserRestControllerImpl implements ServiceUserRestController {

	private final ServiceUserService serviceUserService;

	@Override
	public Page<ServiceUserDTO> getAllServiceUsers(String name, ServiceType serviceType, Integer page,
			Integer pageSize) {
		final ServiceUserSearchOptions searchOptions = ServiceUserSearchOptions.builder().name(name)
				.serviceType(serviceType).page(page).size(pageSize).build();

		return serviceUserService.getAllServiceUsers(searchOptions);
	}

	@Override
	public ServiceUserDTO getServiceUser(final String username) throws ResourceNotFoundException {
		return serviceUserService.getServiceUser(username);
	}

	@Override
	public List<VehicleServiceDTO> getServiceUserServices(final String username) throws ResourceNotFoundException {
		return serviceUserService.getServiceUserServices(username);
	}

	@Override
	public ServiceUserDTO addServiceUser(final ServiceUserDTO personUserDto)
			throws ResourceException, BadRequestException {
		return serviceUserService.addServiceUser(personUserDto);
	}

	@Override
	public void updateServiceUser(final String username, final ServiceUserDTO serviceUserDto)
			throws ResourceException, BadRequestException {
		serviceUserService.updateServiceUser(username, serviceUserDto);
	}

	@Override
	public void deleteServiceUser(final String username) throws ResourceNotFoundException {
		serviceUserService.deleteServiceUser(username);
	}

}
