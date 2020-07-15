package rs.ac.ni.pmf.web.controller;

import java.sql.Date;
import java.util.Set;

import javax.validation.Valid;

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
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;

@RequestMapping(path = "/vehicle")
public interface VehicleRestController {

	// GET
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<VehicleDTO> getAllVehicles(@RequestParam(name = "priceFrom", required = false) final Float priceFrom,
			@RequestParam(name = "priceTo", required = false) final Float priceTo,
			@RequestParam(name = "vehicleCondition", required = false) final Condition conditionFilter,
			@RequestParam(name = "makeYear", required = false) final Date makeYearFilter,
			@RequestParam(name = "bodyStyle", required = false) final BodyStyle bodyStyleFilter,
			@RequestParam(name = "driveType", required = false) final DriveType driveTypeFilter,
			@RequestParam(name = "kilometrageFrom", required = false) final Float kilometrageFrom,
			@RequestParam(name = "kilometrageTo", required = false) final Float kilometrageTo,
			@RequestParam(name = "fuelType", required = false) final FuelType fuelType,
			@RequestParam(name = "engineVolume", required = false) final Integer engineVolume,
			@RequestParam(name = "enginePowerKW", required = false) final Integer enginePowerKW,
			@RequestParam(name = "engineEmissionType", required = false) final EngineEmissionType engineEmissionTypeFilter,
			@RequestParam(name = "gearboxType", required = false) final GearboxType gearboxTypeFilter,
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize);

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
	VehicleDTO addVehicle(@RequestBody @Valid final VehicleDTO vehicleDto)
			throws DuplicateResourceException, ResourceNotFoundException;

	// PUT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{vehicleVin}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void updateVehicle(@RequestBody @Valid final VehicleDTO vehicleDto) throws ResourceException, BadRequestException;

	// DELETE
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{vehicleVin}")
	void deleteVehicle(@PathVariable(name = "vehicleVin") final String vehicleVin) throws ResourceNotFoundException;

}
