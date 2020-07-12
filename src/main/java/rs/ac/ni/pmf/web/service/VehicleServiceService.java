package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity;
import rs.ac.ni.pmf.web.model.mapper.VehicleServiceMapper;
import rs.ac.ni.pmf.web.repository.VehicleRepository;
import rs.ac.ni.pmf.web.repository.VehicleServiceRepository;

@Service
@RequiredArgsConstructor
public class VehicleServiceService {

	private final VehicleServiceRepository vehicleServiceRepository;
	private final VehicleRepository vehicleRepository;
	private final VehicleServiceMapper vehicleServiceMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// READ
	public List<VehicleServiceDTO> getVehicleServices(final String vehicleVin) throws ResourceNotFoundException {
		List<VehicleServiceEntity> vehicleServiceEntities = null;

		try {
			vehicleServiceEntities = vehicleServiceRepository.findByVehicleVin(vehicleVin);
		} catch (Exception e) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE,
					"Services for Vehicle with VIN '" + vehicleVin + "' do not exist.");
		}

		return vehicleServiceEntities.stream().map(vehicleServiceMapper::toDto).collect(Collectors.toList());
	}

	// CREATE
	public VehicleServiceDTO addVehicleService(final String vehicleVin, final VehicleServiceDTO vehicleServiceDto)
			throws BadRequestException, ResourceNotFoundException, DuplicateResourceException {
		if (vehicleVin == null || vehicleVin.trim().isEmpty()) {
			throw new BadRequestException("Vehicle VIN cannot be null or empty.");
		}

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vehicleVin + "' does not exist."));

		final Integer vehicleServiceId = vehicleServiceDto.getId();

		if (vehicleServiceId != null && vehicleServiceRepository.existsById(vehicleServiceId)) {
			throw new DuplicateResourceException(ResourceType.VEHICLE_SPECIFICATION,
					"Vehicle service with id '" + vehicleServiceId + "' already exists.");
		}

		final VehicleServiceEntity vehicleServiceEntity = vehicleServiceMapper.toEntity(vehicleServiceDto,
				vehicleEntity);
		final VehicleServiceEntity savedEntity = vehicleServiceRepository.save(vehicleServiceEntity);

		return vehicleServiceMapper.toDto(savedEntity);
	}

	// UPDATE
	public VehicleServiceDTO updateVehicleService(final String vehicleVin, final VehicleServiceDTO vehicleServiceDto)
			throws BadRequestException, ResourceNotFoundException {
		if (vehicleVin != vehicleServiceDto.getVehicleVin()) {
			throw new BadRequestException("Cannot change the vehicle VIN of a vehicle service.");
		}

		final Integer vehicleServiceId = vehicleServiceDto.getId();

		final VehicleServiceEntity vehicleServiceEntity = vehicleServiceRepository.findById(vehicleServiceId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE_SERVICE,
						"Vehicle service with id '" + vehicleServiceId + "' does not exist."));

		vehicleServiceEntity.setDateTaken(vehicleServiceDto.getDateTaken());
		vehicleServiceEntity.setPrice(vehicleServiceDto.getPrice());
		vehicleServiceEntity.setServiceDetails(vehicleServiceDto.getServiceDetails());

		final VehicleServiceEntity savedEntity = vehicleServiceRepository.save(vehicleServiceEntity);

		return vehicleServiceMapper.toDto(savedEntity);
	}

	// DELETE
	public void deleteVehicleService(final String vehicleVin, final Integer vehicleServiceId)
			throws ResourceNotFoundException, BadRequestException {
		final VehicleServiceEntity vehicleServiceEntity = vehicleServiceRepository.findById(vehicleServiceId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE_SERVICE,
						"Vehicle service with id '" + vehicleServiceId + "' does not exist."));

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vehicleVin + "' does not exist."));

		Boolean vehicleServiceIdMatches = false;

		for (VehicleServiceEntity vse : vehicleEntity.getServices()) {
			if (vse.getId() == vehicleServiceId) {
				vehicleServiceIdMatches = true;
			}
		}

		if (vehicleServiceIdMatches) {

			vehicleServiceRepository.deleteById(vehicleServiceEntity.getId());
		} else {
			throw new BadRequestException("The provided VIN and vehicle service id do not match.");
		}

	}

}
