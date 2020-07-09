package rs.ac.ni.pmf.web.controller;

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
import rs.ac.ni.pmf.web.model.api.SaleListingDTO;

@RequestMapping(path = "/vehicle/{vehicleVin}/sale-listing")
public interface SaleListingController {
	// GET
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	SaleListingDTO getSaleListing(@PathVariable(name = "vehicleVin", required = true) String vehicleVin);

	// POST
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	SaleListingDTO saveSaleListing(@PathVariable(name = "vehicleVin") String vehicleVin,
			@RequestBody SaleListingDTO saleListing) throws DuplicateResourceException, ResourceNotFoundException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	void updateSaleListing(@PathVariable(name = "vehicleVin") String vehicleVin,
			@RequestBody SaleListingDTO saleListing) throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping()
	void deleteSaleListing(@PathVariable(name = "vehicleVin") String vehicleVin) throws ResourceNotFoundException;
}
