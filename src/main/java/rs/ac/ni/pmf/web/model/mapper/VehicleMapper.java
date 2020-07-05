package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;

@Component
public class VehicleMapper {

	public VehicleDTO toDto(final VehicleEntity vehicleEntity) {
		return VehicleDTO.builder().make(vehicleEntity.getMake()).model(vehicleEntity.getModel()).build();
	}

	public VehicleEntity toEntity(final VehicleDTO vehicleDto) {
		return VehicleEntity.builder().make(vehicleDto.getMake()).model(vehicleDto.getModel()).build();
	}
}
