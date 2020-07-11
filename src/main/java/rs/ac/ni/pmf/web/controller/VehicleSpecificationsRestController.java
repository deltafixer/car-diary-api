package rs.ac.ni.pmf.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.ac.ni.pmf.web.model.api.VehicleSpecificationsDTO;

@RequestMapping(path = "/vehicle/{vehicleVin}/specifications")
public interface VehicleSpecificationsRestController {
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	VehicleSpecificationsDTO getVehicleSpecifications(
			@PathVariable(name = "vehicleVin", required = true) final String vehicleVin);
	
}
