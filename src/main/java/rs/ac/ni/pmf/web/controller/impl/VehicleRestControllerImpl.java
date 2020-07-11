package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.service.VehicleService;

@RestController
@RequiredArgsConstructor
public class VehicleRestControllerImpl implements VehicleRestController {

	private final VehicleService vehicleService;

	@Override
	public VehicleDTO getVehicle(final String vin) throws ResourceNotFoundException {
		return vehicleService.getVehicle(vin);
	}

	@Override
	public List<VehicleServiceDTO> getVehicleServices(final String vin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleAccidentDTO> getVehicleAccidents(final String vin) throws ResourceNotFoundException {
		return vehicleService.getVehicleAccidents(vin);
	}

	@Override
	public List<PersonUserDTO> getVehicleOwners(String vin) {
		// TODO Auto-generated method stub
		return null;
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
	public void deleteVehicle(String vin) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

	}

}
