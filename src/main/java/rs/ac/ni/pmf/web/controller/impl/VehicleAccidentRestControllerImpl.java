package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleAccidentRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.service.VehicleAccidentService;

@RestController
@RequiredArgsConstructor
public class VehicleAccidentRestControllerImpl implements VehicleAccidentRestController {

	private final VehicleAccidentService vehicleAccidentService;

	@Override
	public List<VehicleAccidentDTO> getVehicleAccidents(final String vehicleVin) throws ResourceNotFoundException {
		return vehicleAccidentService.getVehicleAccidents(vehicleVin);
	}

	@Override
	public VehicleAccidentDTO addVehicleAccident(final String vehicleVin, final VehicleAccidentDTO vehicleAccidentDto)
			throws ResourceException, BadRequestException {
		return vehicleAccidentService.addVehicleAccident(vehicleVin, vehicleAccidentDto);
	}

	@Override
	public VehicleAccidentDTO updateVehicleAccident(String vehicleVin, VehicleAccidentDTO vehicleAccidentDto)
			throws ResourceException, BadRequestException {
		return vehicleAccidentService.updateVehicleAccident(vehicleVin, vehicleAccidentDto);
	}

	@Override
	public void deleteVehicleAccident(final String vehicleVin, final Integer vehicleAccidentId)
			throws ResourceNotFoundException, BadRequestException {
		vehicleAccidentService.deleteVehicleAccident(vehicleVin, vehicleAccidentId);
	}

}
