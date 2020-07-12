package rs.ac.ni.pmf.web.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;

@RequestMapping(path = "/vehicle")
public interface VehicleRestController {

	// GET
	@GetMapping(path = "/{vehicleVin}", produces = MediaType.APPLICATION_JSON_VALUE)
	VehicleDTO getVehicle(@PathVariable(name = "vehicleVin", required = true) final String vehicleVin)
			throws ResourceNotFoundException;

	// COMMENT: the only reason this function is here and not in
	// PersonUserController is to keep cleaner paths
	@GetMapping(path = "/{vehicleVin}/owners", produces = MediaType.APPLICATION_JSON_VALUE)
	Set<PersonUserDTO> getVehicleOwners(@PathVariable(name = "vehicleVin", required = true) final String vehicleVin)
			throws ResourceNotFoundException;

	// POST
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	VehicleDTO addVehicle(@RequestBody final VehicleDTO vehicleDto)
			throws DuplicateResourceException, ResourceNotFoundException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{vehicleVin}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void updateVehicle(@RequestBody final VehicleDTO vehicleDto) throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{vehicleVin}")
	void deleteVehicle(@PathVariable(name = "vehicleVin") final String vehicleVin) throws ResourceNotFoundException;

}
