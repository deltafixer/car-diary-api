package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleAccidentEntity;

@Component
public class VehicleAccidentMapper {

	public VehicleAccidentDTO toDto(final VehicleAccidentEntity vehicleAccidentEntity) {
		return VehicleAccidentDTO.builder().dateOfAccident(vehicleAccidentEntity.getDateOfAccident())
				.damagePriceEvaluation(vehicleAccidentEntity.getDamagePriceEvaluation()).build();
	}

	public VehicleAccidentEntity toEntity(final VehicleAccidentDTO vehicleAccidentDto) {
		return VehicleAccidentEntity.builder().dateOfAccident(vehicleAccidentDto.getDateOfAccident())
				.damagePriceEvaluation(vehicleAccidentDto.getDamagePriceEvaluation()).build();
	}

}
