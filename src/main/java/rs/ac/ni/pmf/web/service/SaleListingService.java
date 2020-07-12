package rs.ac.ni.pmf.web.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.SaleListingSearchOptions;
import rs.ac.ni.pmf.web.model.api.SaleListingDTO;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity_;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;
import rs.ac.ni.pmf.web.model.entity.VehicleAccidentEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleAccidentEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Model;
import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity_;
import rs.ac.ni.pmf.web.model.mapper.SaleListingMapper;
import rs.ac.ni.pmf.web.repository.SaleListingRepository;
import rs.ac.ni.pmf.web.repository.VehicleRepository;
import rs.ac.ni.pmf.web.repository.VehicleServiceRepository;
import rs.ac.ni.pmf.web.repository.specification.SaleListingSearchSpecification;

@Service
@RequiredArgsConstructor
public class SaleListingService {

	private static final Integer DEFAULT_PAGE_SIZE = 10;
	private static final Integer RECOMMENDED_SERVICE_COUNT_PER_YEAR = 4;
	private static final Integer MAXIMUM_ACCIDENT_COUNT = 4;

	private final SaleListingRepository saleListingRepository;
	private final SaleListingMapper saleListingMapper;
	private final VehicleRepository vehicleRepository;
	private final VehicleServiceRepository vehicleServiceRepository;

	@PersistenceContext
	private EntityManager entityManager;

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

	public SaleListingDTO getSaleListing(final String vehicleVin) throws ResourceNotFoundException {
		SaleListingEntity saleListingEntity = null;

		try {
			saleListingEntity = saleListingRepository.findByVehicleVin(vehicleVin);
		} catch (Exception e) {
			throw new ResourceNotFoundException(ResourceType.SALE_LISTING,
					"Sale listing for vehicle with vin '" + vehicleVin + "' does not exist.");
		}

		return saleListingMapper.toDto(saleListingEntity);
	}

	@Transactional
	public Double getSaleListingSuggestionScore(final String vehicleVin)
			throws ResourceNotFoundException, NotFoundException {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Double> cqAveragePrice = cb.createQuery(Double.class);
		final CriteriaQuery<VehicleServiceEntity> cqServiceCount = cb.createQuery(VehicleServiceEntity.class);
		final CriteriaQuery<Long> cqAccidentCount = cb.createQuery(Long.class);

		final Root<SaleListingEntity> saleListingRoot = cqAveragePrice.from(SaleListingEntity.class);
		final Root<VehicleEntity> vehicleServiceCountRoot = cqServiceCount.from(VehicleEntity.class);
		final Root<VehicleEntity> vehicleAccidentCountRoot = cqAccidentCount.from(VehicleEntity.class);

		final Join<SaleListingEntity, VehicleEntity> saleListingVehicleJoin = saleListingRoot
				.join(SaleListingEntity_.vehicle, JoinType.LEFT);

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with vin '" + vehicleVin + "' does not exist."));

		final SaleListingEntity saleListingEntity = saleListingRepository.findByVehicleVin(vehicleVin);

		// GET AVERAGE PRICE FOR THIS MODEL
		final Model vehicleModel = vehicleEntity.getModel();

		cqAveragePrice.select(cb.avg(saleListingRoot.get(SaleListingEntity_.price)));
		cqAveragePrice.where(cb.equal(saleListingVehicleJoin.get(VehicleEntity_.model), vehicleModel));

		final Double vehicleAveragePrice = entityManager.createQuery(cqAveragePrice).getSingleResult();

		// GET SERVICES COUNT FOR THIS VEHICLE
		final Join<VehicleEntity, VehicleServiceEntity> vehicleVehicleServiceJoin = vehicleServiceCountRoot
				.join(VehicleEntity_.services, JoinType.LEFT);

		cqServiceCount.multiselect(vehicleServiceCountRoot);
		cqServiceCount.where(cb.equal(vehicleVehicleServiceJoin.get(VehicleServiceEntity_.vehicle), vehicleEntity));

//		final List<VehicleServiceEntity> vehicleServices = entityManager.createQuery(cqServiceCount).getResultList();
		final List<VehicleServiceEntity> vehicleServices = vehicleServiceRepository.findByVehicleVin(vehicleVin);

		Integer authorizedServicesCount = 0;

		System.out.println(vehicleServices.size());

		for (VehicleServiceEntity vse : vehicleServices) {
			if (vse.getServicedBy().getServiceType() == ServiceType.AUTHORIZED) {
				++authorizedServicesCount;
			}
		}

		// GET ACCIDENTS COUNT FOR THIS VEHICLE
		final Join<VehicleEntity, VehicleAccidentEntity> vehicleVehicleAccidentJoin = vehicleAccidentCountRoot
				.join(VehicleEntity_.accidents, JoinType.LEFT);

		cqAccidentCount.select(cb.count(vehicleAccidentCountRoot.get(VehicleEntity_.vin)));
		cqAccidentCount.where(cb.equal(vehicleVehicleAccidentJoin.get(VehicleAccidentEntity_.vehicle), vehicleEntity));

		final Long vehicleAccidentsCount = entityManager.createQuery(cqAccidentCount).getSingleResult();

		System.out.println("Average price: " + vehicleAveragePrice + " services count: " + vehicleServices.size()
				+ " accidentsCount: " + vehicleAccidentsCount);

		Double priceScore = (-((vehicleAveragePrice - saleListingEntity.getPrice()) / vehicleAveragePrice) + 0.5) * 30;

		final Integer vehicleServiceCount = vehicleServices.size();
		final Date today = new Date();

		@SuppressWarnings("deprecation")
		Double serviceScore = (double) (vehicleServiceCount / (RECOMMENDED_SERVICE_COUNT_PER_YEAR
				* (today.getYear() - vehicleEntity.getSpecifications().getMakeYear().getYear()))) * 40
				* (vehicleServiceCount / (vehicleServiceCount - authorizedServicesCount));
		
		Double accidentScore = (double) ((MAXIMUM_ACCIDENT_COUNT - vehicleAccidentsCount) / MAXIMUM_ACCIDENT_COUNT);

		return priceScore + serviceScore + accidentScore;
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
			throw new BadRequestException("Cannot change the vehicle VIN of a sale listing.");
		}

		final Integer id = saleListingDto.getId();

		final SaleListingEntity saleListingEntity = saleListingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.SALE_LISTING,
						"Sale listing with id '" + id + "' does not exist."));

		saleListingEntity.setPrice(saleListingDto.getPrice());
		saleListingEntity.setDateAdded(saleListingDto.getDateAdded());

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

		if (saleListingEntity != null && !saleListingRepository.existsById(saleListingEntity.getId())) {
			throw new ResourceNotFoundException(ResourceType.SALE_LISTING,
					"Sale listing for vehicle with vin '" + vehicleVin + "' does not exist.");
		}

		saleListingRepository.deleteById(saleListingEntity.getId());
	}

}