package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.SaleListingDTO;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;

@Component
public class SaleListingMapper {

	public SaleListingDTO toDto(final SaleListingEntity saleListingEntity) {
		return SaleListingDTO.builder().price(saleListingEntity.getPrice()).dateAdded(saleListingEntity.getDateAdded())
				.suggestionScore(saleListingEntity.getSuggestionScore()).build();
	}

	public SaleListingEntity toEntity(final SaleListingDTO saleListingDto) {
		return SaleListingEntity.builder().price(saleListingDto.getPrice()).dateAdded(saleListingDto.getDateAdded())
				.suggestionScore(saleListingDto.getSuggestionScore()).build();
	}
}
