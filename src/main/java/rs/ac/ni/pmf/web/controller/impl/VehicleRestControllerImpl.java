package rs.ac.ni.pmf.web.controller.impl;

import java.util.Set;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.service.VehicleService;

@RestController
@RequiredArgsConstructor
public class VehicleRestControllerImpl implements VehicleRestController {

	private final VehicleService vehicleService;

	@Override
	public VehicleDTO getVehicle(final String vehicleVin) throws ResourceNotFoundException {
		return vehicleService.getVehicle(vehicleVin);
	}

	@Override
	public Set<PersonUserDTO> getVehicleOwners(String vehicleVin) throws ResourceNotFoundException {
		return vehicleService.getVehicleOwners(vehicleVin);
	}

	@Override
	public VehicleDTO addVehicle(final VehicleDTO vehicleDto)
			throws DuplicateResourceException, ResourceNotFoundException {
		return vehicleService.addVehicle(vehicleDto);
	}

	@Override
	public void updateVehicle(VehicleDTO vehicle) throws ResourceException, BadRequestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteVehicle(String vehicleVin) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

	}

}
