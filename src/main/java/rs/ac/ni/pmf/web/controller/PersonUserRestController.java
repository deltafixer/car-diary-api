package rs.ac.ni.pmf.web.controller;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;

// COMMENT:
// Either a regular user or an admin (both are a single person, thus the name, "person-user").

@RequestMapping(path = "/person-user")
public interface PersonUserRestController {

	// GET
	// COMMENT: Admin should be able to list all users.
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<PersonUserDTO> getAllPersonUsers(
			@RequestParam(name = "firstName", required = false) final String firstNameFilter,
			@RequestParam(name = "lastName", required = false) final String lastNameFilter,
			@RequestParam(name = "minVehicleCount", required = false) final Integer minVehicleCount,
			@RequestParam(name = "maxVehicleCount", required = false) final Integer maxVehicleCount,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize);

	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	PersonUserDTO getPersonUser(@PathVariable(name = "username", required = true) final String username)
			throws ResourceNotFoundException;

	@GetMapping(path = "/{username}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	Set<VehicleDTO> getPersonUserVehicles(@PathVariable(name = "username", required = true) final String username)
			throws ResourceNotFoundException;

	// POST
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	PersonUserDTO addPersonUser(@RequestBody final PersonUserDTO personUser)
			throws ResourceException, BadRequestException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void updatePersonUser(@PathVariable(name = "username") final String username,
			@RequestBody final PersonUserDTO personUser) throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{username}")
	void deletePersonUser(@PathVariable(name = "username") final String username) throws ResourceNotFoundException;

}

//	COMMENT: Teapot :D
//	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)