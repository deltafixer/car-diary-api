package rs.ac.ni.pmf.web.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rs.ac.ni.pmf.web.model.api.RegularUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;

@RequestMapping(path = "/regular-user")
public interface RegularUserRestController {
	// GET
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<RegularUserDTO> getRegularUsers(
			@RequestParam(name = "firstName", required = false) final String firstNameFilter,
			@RequestParam(name = "lastName", required = false) final String lastNameFilter,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize);

//	COMMENT: Teapot :D
//	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	RegularUserDTO getRegularUser(@PathVariable(name = "username", required = true) String username);
	
	@GetMapping(path = "/{username}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VehicleDTO> getServiceUserVehicles(@PathVariable(name = "username", required = true) String username);
}
