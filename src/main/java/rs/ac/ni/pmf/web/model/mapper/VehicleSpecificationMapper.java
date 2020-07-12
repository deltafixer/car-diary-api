package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.VehicleSpecificationDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleSpecificationEntity;

@Component
public class VehicleSpecificationMapper {

	public VehicleSpecificationDTO toDto(final VehicleSpecificationEntity vehicleSpecificationEntity) {
		return VehicleSpecificationDTO.builder().price(vehicleSpecificationEntity.getPrice())
				.vehicleCondition(vehicleSpecificationEntity.getVehicleCondition())
				.makeYear(vehicleSpecificationEntity.getMakeYear()).bodyStyle(vehicleSpecificationEntity.getBodyStyle())
				.driveType(vehicleSpecificationEntity.getDriveType())
				.kilometrage(vehicleSpecificationEntity.getKilometrage())
				.fuelType(vehicleSpecificationEntity.getFuelType())
				.engineVolume(vehicleSpecificationEntity.getEngineVolume())
				.enginePowerKW(vehicleSpecificationEntity.getEnginePowerKW())
				.engineEmissionType(vehicleSpecificationEntity.getEngineEmissionType())
				.gearboxType(vehicleSpecificationEntity.getGearboxType()).build();
	}

	public VehicleSpecificationEntity toEntity(final VehicleSpecificationDTO vehicleSpecificationDto,
			final VehicleEntity vehicle) {
		return VehicleSpecificationEntity.builder().price(vehicleSpecificationDto.getPrice())
				.vehicleCondition(vehicleSpecificationDto.getVehicleCondition())
				.makeYear(vehicleSpecificationDto.getMakeYear()).bodyStyle(vehicleSpecificationDto.getBodyStyle())
				.driveType(vehicleSpecificationDto.getDriveType()).kilometrage(vehicleSpecificationDto.getKilometrage())
				.fuelType(vehicleSpecificationDto.getFuelType()).engineVolume(vehicleSpecificationDto.getEngineVolume())
				.enginePowerKW(vehicleSpecificationDto.getEnginePowerKW())
				.engineEmissionType(vehicleSpecificationDto.getEngineEmissionType())
				.gearboxType(vehicleSpecificationDto.getGearboxType()).vehicle(vehicle).build();
	}

}
