package rs.ac.ni.pmf.web.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleSpecificationRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.VehicleSpecificationDTO;
import rs.ac.ni.pmf.web.service.VehicleSpecificationService;

@RestController
@RequiredArgsConstructor
public class VehicleSpecificationRestControllerImpl implements VehicleSpecificationRestController {

	private final VehicleSpecificationService vehicleSpecificationService;

	@Override
	public VehicleSpecificationDTO getVehicleSpecification(final String vehicleVin) throws ResourceNotFoundException {
		return vehicleSpecificationService.getVehicleSpecification(vehicleVin);
	}

	@Override
	public VehicleSpecificationDTO addVehicleSpecification(final String vehicleVin,
			final VehicleSpecificationDTO vehicleSpecificationDto) throws ResourceException, BadRequestException {
		return vehicleSpecificationService.addVehicleSpecification(vehicleVin, vehicleSpecificationDto);
	}

	@Override
	public VehicleSpecificationDTO updateVehicleSpecification(String vehicleVin,
			VehicleSpecificationDTO vehicleSpecificationDto) throws ResourceException, BadRequestException {
		return vehicleSpecificationService.updateVehicleSpecification(vehicleVin, vehicleSpecificationDto);
	}

	@Override
	public void deleteVehicleSpecification(String vehicleVin) throws ResourceNotFoundException {
		vehicleSpecificationService.deleteVehicleSpecification(vehicleVin);
	}

}
