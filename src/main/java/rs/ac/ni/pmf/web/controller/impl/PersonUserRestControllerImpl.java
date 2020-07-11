package rs.ac.ni.pmf.web.controller.impl;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.PersonUserRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.PersonUserSearchOptions;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.service.PersonUserService;

@RestController
@RequiredArgsConstructor
public class PersonUserRestControllerImpl implements PersonUserRestController {

	private final PersonUserService personUserService;

	@Override
	public Page<PersonUserDTO> getAllPersonUsers(final String firstNameFilter, final String lastNameFilter,
			final Integer minVehicleCount, final Integer maxVehicleCount, final Integer page, final Integer pageSize) {
		final PersonUserSearchOptions searchOptions = PersonUserSearchOptions.builder().firstNameFilter(firstNameFilter)
				.lastNameFilter(lastNameFilter).minVehicleCount(minVehicleCount).maxVehicleCount(maxVehicleCount)
				.page(page).size(pageSize).build();

		return personUserService.getAllPersonUsers(searchOptions);
	}

	@Override
	public PersonUserDTO getPersonUser(final String username) throws ResourceNotFoundException {
		return personUserService.getPersonUser(username);
	}

	@Override
	public Set<VehicleDTO> getPersonUserVehicles(final String username) throws ResourceNotFoundException {
		return personUserService.getPersonUserVehicles(username);
	}

	@Override
	public PersonUserDTO addPersonUser(final PersonUserDTO personUserDto)
			throws ResourceException, BadRequestException {
		return personUserService.addPersonUser(personUserDto);
	}

	@Override
	public void updatePersonUser(final String username, final PersonUserDTO personUserDto)
			throws ResourceException, BadRequestException {
		personUserService.updatePersonUser(username, personUserDto);
	}

	@Override
	public void deletePersonUser(final String username) throws ResourceNotFoundException {
		personUserService.deletePersonUser(username);
	}

}
