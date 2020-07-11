package rs.ac.ni.pmf.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.SaleListingSearchOptions;
import rs.ac.ni.pmf.web.model.api.SaleListingDTO;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.mapper.SaleListingMapper;
import rs.ac.ni.pmf.web.repository.SaleListingRepository;
import rs.ac.ni.pmf.web.repository.VehicleRepository;
import rs.ac.ni.pmf.web.repository.specification.SaleListingSearchSpecification;

@Service
@RequiredArgsConstructor
public class SaleListingService {

	private static final Integer DEFAULT_PAGE_SIZE = 10;

	private final SaleListingRepository saleListingRepository;
	private final SaleListingMapper saleListingMapper;
	private final VehicleRepository vehicleRepository;

	// READ
	public Page<SaleListingDTO> getAllSaleListings(final SaleListingSearchOptions searchOptions) {

		final PageRequest pageRequest;

		if (searchOptions.getPage() != null) {
			pageRequest = PageRequest.of(searchOptions.getPage(),
					searchOptions.getSize() != null ? searchOptions.getSize() : DEFAULT_PAGE_SIZE);
		} else {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}

		return saleListingRepository.findAll(new SaleListingSearchSpecification(searchOptions), pageRequest)
				.map(saleListingMapper::toDto);
	}

	public SaleListingDTO getSaleListing(String vehicleVin) throws ResourceNotFoundException {
		SaleListingEntity saleListingEntity = null;

		try {
			saleListingEntity = saleListingRepository.findByVehicleVin(vehicleVin);
		} catch (Exception e) {
			throw new ResourceNotFoundException(ResourceType.SALE_LISTING,
					"Sale listing for vehicle with vin '" + vehicleVin + "' does not exist.");
		}

		return saleListingMapper.toDto(saleListingEntity);
	}

	// CREATE
	public SaleListingDTO saveSaleListing(final String vehicleVin, final SaleListingDTO saleListingDto)
			throws ResourceException, BadRequestException {
		if (vehicleVin == null || vehicleVin.trim().isEmpty()) {
			throw new BadRequestException("Vehicle vin cannot be null or empty.");
		}

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle '" + vehicleVin + "' does not exist."));

		final Integer saleListingId = saleListingDto.getId();

		if (saleListingId != null && saleListingRepository.existsById(saleListingId)) {
			throw new DuplicateResourceException(ResourceType.SALE_LISTING,
					"Sale listing with id '" + saleListingId + "' already exists.");
		}

		final SaleListingEntity saleListingEntity = saleListingMapper.toEntity(saleListingDto, vehicleEntity);
		final SaleListingEntity savedEntity = saleListingRepository.save(saleListingEntity);

		return saleListingMapper.toDto(savedEntity);
	}

	// UPDATE
	public SaleListingDTO updateSaleListing(final String vehicleVin, final SaleListingDTO saleListingDto)
			throws ResourceException, BadRequestException {

		if (vehicleVin != saleListingDto.getVehicleVin()) {
			throw new BadRequestException("Cannot change the vehicle vin of a sale listing.");
		}

		final Integer id = saleListingDto.getId();

		final SaleListingEntity saleListingEntity = saleListingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.SALE_LISTING,
						"Sale listing with id '" + id + "' does not exist."));

		saleListingEntity.setPrice(saleListingDto.getPrice());
		saleListingEntity.setDateAdded(saleListingDto.getDateAdded());
		saleListingEntity.setSuggestionScore(saleListingDto.getSuggestionScore());

		final SaleListingEntity savedEntity = saleListingRepository.save(saleListingEntity);

		return saleListingMapper.toDto(savedEntity);
	}

	// DELETE
	public void deleteSaleListing(final String vehicleVin) throws ResourceNotFoundException {
		SaleListingEntity saleListingEntity = null;

		try {
			saleListingEntity = saleListingRepository.findByVehicleVin(vehicleVin);
		} catch (Exception e) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE,
					"Vehicle with vin '" + vehicleVin + "' does not exist.");
		}

		if (saleListingEntity != null && saleListingRepository.existsById(saleListingEntity.getId())) {
			throw new ResourceNotFoundException(ResourceType.SALE_LISTING,
					"Sale listing for vehicle with vin '" + vehicleVin + "' does not exist.");
		}

		saleListingRepository.deleteById(saleListingEntity.getId());
	}

}