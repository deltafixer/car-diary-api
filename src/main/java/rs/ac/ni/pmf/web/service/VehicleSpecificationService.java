package rs.ac.ni.pmf.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.VehicleSpecificationDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleSpecificationEntity;
import rs.ac.ni.pmf.web.model.mapper.VehicleSpecificationMapper;
import rs.ac.ni.pmf.web.repository.VehicleRepository;
import rs.ac.ni.pmf.web.repository.VehicleSpecificationRepository;

@Service
@RequiredArgsConstructor
public class VehicleSpecificationService {

	private final VehicleSpecificationRepository vehicleSpecificationRepository;
	private final VehicleRepository vehicleRepository;
	private final VehicleSpecificationMapper vehicleSpecificationMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// READ
	public VehicleSpecificationDTO getVehicleSpecification(final String vehicleVin) throws ResourceNotFoundException {
		VehicleSpecificationEntity vehicleSpecificationEntity = null;

		try {
			vehicleSpecificationEntity = vehicleSpecificationRepository.findByVehicleVin(vehicleVin);
		} catch (Exception e) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE,
					"Specification for Vehicle with VIN '" + vehicleVin + "' does not exist.");
		}

		return vehicleSpecificationMapper.toDto(vehicleSpecificationEntity);
	}

	// CREATE
	public VehicleSpecificationDTO addVehicleSpecification(final String vehicleVin,
			final VehicleSpecificationDTO vehicleSpecificationDto)
			throws BadRequestException, ResourceNotFoundException, DuplicateResourceException {
		if (vehicleVin == null || vehicleVin.trim().isEmpty()) {
			throw new BadRequestException("Vehicle VIN cannot be null or empty.");
		}

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vehicleVin + "' does not exist."));

		final Integer vehicleSpecificationId = vehicleSpecificationDto.getId();

		if (vehicleSpecificationId != null && vehicleSpecificationRepository.existsById(vehicleSpecificationId)) {
			throw new DuplicateResourceException(ResourceType.VEHICLE_SPECIFICATION,
					"Vehicle specification with id '" + vehicleSpecificationId + "' already exists.");
		}

		final VehicleSpecificationEntity vehicleSpecificationEntity = vehicleSpecificationMapper
				.toEntity(vehicleSpecificationDto, vehicleEntity);
		final VehicleSpecificationEntity savedEntity = vehicleSpecificationRepository.save(vehicleSpecificationEntity);

		return vehicleSpecificationMapper.toDto(savedEntity);
	}

	// UPDATE
	public VehicleSpecificationDTO updateVehicleSpecification(final String vehicleVin,
			final VehicleSpecificationDTO vehicleSpecificationDto)
			throws BadRequestException, ResourceNotFoundException {
		if (vehicleVin != vehicleSpecificationDto.getVehicleVin()) {
			throw new BadRequestException("Cannot change the vehicle VIN of a vehicle specification.");
		}

		final Integer vehicleSpecificationId = vehicleSpecificationDto.getId();

		final VehicleSpecificationEntity vehicleSpecificationEntity = vehicleSpecificationRepository
				.findById(vehicleSpecificationId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.SALE_LISTING,
						"Vehicle specification with id '" + vehicleSpecificationId + "' does not exist."));

		vehicleSpecificationEntity.setPrice(vehicleSpecificationDto.getPrice());
		vehicleSpecificationEntity.setVehicleCondition(vehicleSpecificationDto.getVehicleCondition());
		vehicleSpecificationEntity.setMakeYear(vehicleSpecificationDto.getMakeYear());
		vehicleSpecificationEntity.setBodyStyle(vehicleSpecificationDto.getBodyStyle());
		vehicleSpecificationEntity.setDriveType(vehicleSpecificationDto.getDriveType());
		vehicleSpecificationEntity.setKilometrage(vehicleSpecificationDto.getKilometrage());
		vehicleSpecificationEntity.setFuelType(vehicleSpecificationDto.getFuelType());
		vehicleSpecificationEntity.setEngineVolume(vehicleSpecificationDto.getEngineVolume());
		vehicleSpecificationEntity.setEnginePowerKW(vehicleSpecificationDto.getEnginePowerKW());
		vehicleSpecificationEntity.setEngineEmissionType(vehicleSpecificationDto.getEngineEmissionType());
		vehicleSpecificationEntity.setGearboxType(vehicleSpecificationDto.getGearboxType());

		final VehicleSpecificationEntity savedEntity = vehicleSpecificationRepository.save(vehicleSpecificationEntity);

		return vehicleSpecificationMapper.toDto(savedEntity);
	}

	public void deleteVehicleSpecification(final String vehicleVin) throws ResourceNotFoundException {
		VehicleSpecificationEntity vehicleSpecificationEntity = null;

		try {
			vehicleSpecificationEntity = vehicleSpecificationRepository.findByVehicleVin(vehicleVin);
		} catch (Exception e) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE_SPECIFICATION,
					"Specification for vehicle with with VIN '" + vehicleVin + "' does not exist.");
		}

		if (vehicleSpecificationEntity != null
				&& vehicleSpecificationRepository.existsById(vehicleSpecificationEntity.getId())) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE_SPECIFICATION,
					"Sale listing for vehicle with vin '" + vehicleVin + "' does not exist.");
		}

		vehicleSpecificationRepository.deleteById(vehicleSpecificationEntity.getId());
	}

}
