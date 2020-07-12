package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleServiceRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.service.VehicleServiceService;

@RestController
@RequiredArgsConstructor
public class VehicleServiceRestControllerImpl implements VehicleServiceRestController {

	private final VehicleServiceService vehicleServiceService;

	@Override
	public List<VehicleServiceDTO> getVehicleServices(final String vehicleVin) throws ResourceNotFoundException {
		return vehicleServiceService.getVehicleServices(vehicleVin);
	}

	@Override
	public VehicleServiceDTO addVehicleService(final String vehicleVin, final VehicleServiceDTO vehicleServiceDto)
			throws ResourceException, BadRequestException {
		return vehicleServiceService.addVehicleService(vehicleVin, vehicleServiceDto);
	}

	@Override
	public VehicleServiceDTO updateVehicleService(String vehicleVin, VehicleServiceDTO vehicleServiceDto)
			throws ResourceException, BadRequestException {
		return vehicleServiceService.updateVehicleService(vehicleVin, vehicleServiceDto);
	}

	@Override
	public void deleteVehicleService(final String vehicleVin, final Integer vehicleServiceId)
			throws ResourceNotFoundException, BadRequestException {
		vehicleServiceService.deleteVehicleService(vehicleVin, vehicleServiceId);
	}

}
