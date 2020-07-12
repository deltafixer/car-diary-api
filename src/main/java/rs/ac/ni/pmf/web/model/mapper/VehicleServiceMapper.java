package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity;

@Component
public class VehicleServiceMapper {

	public VehicleServiceDTO toDto(final VehicleServiceEntity vehicleServiceEntity) {
		return VehicleServiceDTO.builder().dateTaken(vehicleServiceEntity.getDateTaken())
				.price(vehicleServiceEntity.getPrice()).serviceDetails(vehicleServiceEntity.getServiceDetails())
				.build();
	}

	public VehicleServiceEntity toEntity(final VehicleServiceDTO vehicleServiceDto, final VehicleEntity vehicle) {
		return VehicleServiceEntity.builder().vehicle(
				vehicle).dateTaken(vehicleServiceDto.getDateTaken())
				.price(vehicleServiceDto.getPrice()).serviceDetails(vehicleServiceDto.getServiceDetails()).build();
	}

}
