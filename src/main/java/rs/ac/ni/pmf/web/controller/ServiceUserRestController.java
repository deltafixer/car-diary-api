package rs.ac.ni.pmf.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rs.ac.ni.pmf.web.model.api.ServiceUserDTO;

@RequestMapping(path = "/service-user")
public interface ServiceUserRestController {

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<ServiceUserDTO> getAllServiceUsers(
			@RequestParam(name = "name", required = false) final String nameFilter,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize);

	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	ServiceUserDTO getServiceUser(@PathVariable(name = "username", required = true) final String username);
	
}
