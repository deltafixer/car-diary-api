package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.RegularUserDTO;
import rs.ac.ni.pmf.web.model.api.UserVehicleDTO;
import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;

@RestController
@RequiredArgsConstructor
public class VehicleRestControllerImpl implements VehicleRestController {
	@Override
	public VehicleDTO getVehicle(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleServiceDTO> getVehicleServices(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleAccidentDTO> getVehicleAccidents(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegularUserDTO> getVehicleOwners(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleDTO saveVehicle(UserVehicleDTO userVehicle)
			throws DuplicateResourceException, ResourceNotFoundException {
		System.out.println(userVehicle);
		// TODO Auto-generated method stub
		return null;
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
