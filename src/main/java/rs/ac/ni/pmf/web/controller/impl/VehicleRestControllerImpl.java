package rs.ac.ni.pmf.web.controller.impl;

import java.sql.Date;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.VehicleRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.VehicleSearchOptions;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;
import rs.ac.ni.pmf.web.service.VehicleService;

@RestController
@RequiredArgsConstructor
public class VehicleRestControllerImpl implements VehicleRestController {

	private final VehicleService vehicleService;

	@Override
	public Page<VehicleDTO> getAllVehicles(final Float priceFrom, final Float priceTo, final Condition conditionFilter,
			final Date makeYearFilter, final BodyStyle bodyStyleFilter, final DriveType driveTypeFilter,
			final Float kilometrageFrom, final Float kilometrageTo, final FuelType fuelType, final Integer engineVolume,
			final Integer enginePowerKW, final EngineEmissionType engineEmissionTypeFilter,
			final GearboxType gearboxTypeFilter, final Integer page, final Integer pageSize) {
		final VehicleSearchOptions searchOptions = VehicleSearchOptions.builder().priceFrom(priceFrom).priceTo(priceTo)
				.conditionFilter(conditionFilter).makeYearFilter(makeYearFilter).bodyStyleFilter(bodyStyleFilter)
				.driveTypeFilter(driveTypeFilter).kilometrageFrom(kilometrageFrom).kilometrageTo(kilometrageTo)
				.fuelType(fuelType).engineVolume(engineVolume).enginePowerKW(enginePowerKW)
				.engineEmissionTypeFilter(engineEmissionTypeFilter).gearboxTypeFilter(gearboxTypeFilter).page(page)
				.size(pageSize).build();

		return vehicleService.getAllVehicles(searchOptions);
	}

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
