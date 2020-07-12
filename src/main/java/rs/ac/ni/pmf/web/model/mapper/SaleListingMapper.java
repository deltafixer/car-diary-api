package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.SaleListingDTO;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;

@Component
public class SaleListingMapper {

	public SaleListingDTO toDto(final SaleListingEntity saleListingEntity) {
		return SaleListingDTO.builder().price(saleListingEntity.getPrice()).dateAdded(saleListingEntity.getDateAdded()).build();
	}

	public SaleListingEntity toEntity(final SaleListingDTO saleListingDto, final VehicleEntity vehicleEntity) {
		return SaleListingEntity.builder().id(saleListingDto.getId()).vehicle(vehicleEntity)
				.price(saleListingDto.getPrice()).dateAdded(saleListingDto.getDateAdded()).build();
	}

}
