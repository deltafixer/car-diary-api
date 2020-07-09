package rs.ac.ni.pmf.web.controller;

import java.util.List;

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
import rs.ac.ni.pmf.web.model.api.RegularUserDTO;
import rs.ac.ni.pmf.web.model.api.UserVehicleDTO;
import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;

@RequestMapping(path = "/vehicle")
public interface VehicleRestController {
	// GET
	@GetMapping(path = "/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
	VehicleDTO getVehicle(@PathVariable(name = "vin", required = true) String vin);

	@GetMapping(path = "/{vin}/services", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VehicleServiceDTO> getVehicleServices(@PathVariable(name = "vin", required = true) String vin);

	@GetMapping(path = "/{vin}/accidents", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VehicleAccidentDTO> getVehicleAccidents(@PathVariable(name = "vin", required = true) String vin);

	@GetMapping(path = "/{vin}/owners", produces = MediaType.APPLICATION_JSON_VALUE)
	List<RegularUserDTO> getVehicleOwners(@PathVariable(name = "vin", required = true) String vin);

	// POST
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	VehicleDTO saveVehicle(@RequestBody UserVehicleDTO userVehicle)
			throws DuplicateResourceException, ResourceNotFoundException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{vin}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void updateVehicle(@RequestBody VehicleDTO vehicle) throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{vin}")
	void deleteVehicle(@PathVariable(name = "vin") String vin) throws ResourceNotFoundException;
}
