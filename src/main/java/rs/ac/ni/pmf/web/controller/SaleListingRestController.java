package rs.ac.ni.pmf.web.controller;

import java.sql.Date;

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

import javassist.NotFoundException;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.SaleListingDTO;

//COMMENT:
//	A sale listing can only be fetched via a vehicle.
//	One cannot get some user's sale listings.

@RequestMapping(path = "/sale-listing")
public interface SaleListingRestController {

	// GET
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<SaleListingDTO> getAllSaleListings(@RequestParam(name = "minPrice", required = false) final Float minPrice,
			@RequestParam(name = "maxPrice", required = false) final Float maxPrice,
			@RequestParam(name = "fromDate", required = false) final Date fromDate,
			@RequestParam(name = "toDate", required = false) final Date toDate,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize);

	@GetMapping(path = "/vehicle/{vehicleVin}", produces = MediaType.APPLICATION_JSON_VALUE)
	SaleListingDTO getSaleListing(@PathVariable(name = "vehicleVin", required = true) final String vehicleVin)
			throws ResourceNotFoundException;

	@GetMapping(path = "/vehicle/{vehicleVin}/suggestionScore", produces = MediaType.APPLICATION_JSON_VALUE)
	Double getSaleListingSuggestionScore(@PathVariable(name = "vehicleVin", required = true) final String vehicleVin)
			throws ResourceNotFoundException, NotFoundException;

	// POST
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/vehicle/{vehicleVin}", consumes = MediaType.APPLICATION_JSON_VALUE)
	SaleListingDTO saveSaleListing(@PathVariable(name = "vehicleVin") final String vehicleVin,
			@RequestBody final SaleListingDTO saleListingDto) throws ResourceException, BadRequestException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/vehicle/{vehicleVin}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void updateSaleListing(@PathVariable(name = "vehicleVin") final String vehicleVin,
			@RequestBody final SaleListingDTO saleListingDto) throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/vehicle/{vehicleVin}")
	void deleteSaleListing(@PathVariable(name = "vehicleVin") final String vehicleVin) throws ResourceNotFoundException;

}
