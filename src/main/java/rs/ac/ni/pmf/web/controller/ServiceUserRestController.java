package rs.ac.ni.pmf.web.controller;

import java.util.List;

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
import rs.ac.ni.pmf.web.model.api.ServiceUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@RequestMapping(path = "/service-user")
public interface ServiceUserRestController {

	// GET
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<ServiceUserDTO> getAllServiceUsers(@RequestParam(name = "name", required = false) final String nameFilter,
			@RequestParam(name = "name", required = false) final ServiceType serviceTypeFilter,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize);

	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	ServiceUserDTO getServiceUser(@PathVariable(name = "username", required = true) final String username)
			throws ResourceNotFoundException;

	@GetMapping(path = "/{username}/services", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VehicleServiceDTO> getServiceUserServices(String username) throws ResourceNotFoundException;

	// POST
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	ServiceUserDTO addServiceUser(@RequestBody final ServiceUserDTO serviceUserDto)
			throws ResourceException, BadRequestException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	void updateServiceUser(String username, ServiceUserDTO serviceUserDto)
			throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{username}")
	void deleteServiceUser(@PathVariable(name = "username") final String username) throws ResourceNotFoundException;

}
