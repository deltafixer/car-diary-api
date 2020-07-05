package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.VehicleSpecificationsDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleSpecificationsEntity;

@Component
public class VehicleSpecificationsMapper {

	public VehicleSpecificationsDTO toDto(final VehicleSpecificationsEntity vehicleSpecificationsEntity) {
		return VehicleSpecificationsDTO.builder().price(vehicleSpecificationsEntity.getPrice())
				.vehicleCondition(vehicleSpecificationsEntity.getVehicleCondition())
				.makeYear(vehicleSpecificationsEntity.getMakeYear())
				.bodyStyle(vehicleSpecificationsEntity.getBodyStyle())
				.driveType(vehicleSpecificationsEntity.getDriveType())
				.kilometrage(vehicleSpecificationsEntity.getKilometrage())
				.fuelType(vehicleSpecificationsEntity.getFuelType())
				.engineVolume(vehicleSpecificationsEntity.getEngineVolume())
				.enginePowerKW(vehicleSpecificationsEntity.getEnginePowerKW())
				.engineEmissionType(vehicleSpecificationsEntity.getEngineEmissionType())
				.gearboxType(vehicleSpecificationsEntity.getGearboxType()).build();
	}

	public VehicleSpecificationsEntity toEntity(final VehicleSpecificationsDTO vehicleSpecificationsDto) {
		return VehicleSpecificationsEntity.builder().price(vehicleSpecificationsDto.getPrice())
				.vehicleCondition(vehicleSpecificationsDto.getVehicleCondition())
				.makeYear(vehicleSpecificationsDto.getMakeYear())
				.bodyStyle(vehicleSpecificationsDto.getBodyStyle())
				.driveType(vehicleSpecificationsDto.getDriveType())
				.kilometrage(vehicleSpecificationsDto.getKilometrage())
				.fuelType(vehicleSpecificationsDto.getFuelType())
				.engineVolume(vehicleSpecificationsDto.getEngineVolume())
				.enginePowerKW(vehicleSpecificationsDto.getEnginePowerKW())
				.engineEmissionType(vehicleSpecificationsDto.getEngineEmissionType())
				.gearboxType(vehicleSpecificationsDto.getGearboxType()).build();
	}

}
