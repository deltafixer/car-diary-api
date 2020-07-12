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
import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleAccidentEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.mapper.VehicleAccidentMapper;
import rs.ac.ni.pmf.web.repository.VehicleAccidentRepository;
import rs.ac.ni.pmf.web.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleAccidentService {

	private final VehicleAccidentRepository vehicleAccidentRepository;
	private final VehicleRepository vehicleRepository;
	private final VehicleAccidentMapper vehicleAccidentMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// READ
	public List<VehicleAccidentDTO> getVehicleAccidents(final String vehicleVin) throws ResourceNotFoundException {
		return vehicleAccidentRepository.findByVehicleVin(vehicleVin).stream().map(vehicleAccidentMapper::toDto)
				.collect(Collectors.toList());
	}

	// CREATE
	public VehicleAccidentDTO addVehicleAccident(final String vehicleVin, final VehicleAccidentDTO vehicleAccidentDto)
			throws BadRequestException, ResourceNotFoundException, DuplicateResourceException {
		if (vehicleVin == null || vehicleVin.trim().isEmpty()) {
			throw new BadRequestException("Vehicle VIN cannot be null or empty.");
		}

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vehicleVin + "' does not exist."));

		final Integer vehicleAccidentId = vehicleAccidentDto.getId();

		if (vehicleAccidentId != null && vehicleAccidentRepository.existsById(vehicleAccidentId)) {
			throw new DuplicateResourceException(ResourceType.VEHICLE_SPECIFICATION,
					"Vehicle service with id '" + vehicleAccidentId + "' already exists.");
		}

		final VehicleAccidentEntity vehicleAccidentEntity = vehicleAccidentMapper.toEntity(vehicleAccidentDto,
				vehicleEntity);
		final VehicleAccidentEntity savedEntity = vehicleAccidentRepository.save(vehicleAccidentEntity);

		return vehicleAccidentMapper.toDto(savedEntity);
	}

	// UPDATE
	public VehicleAccidentDTO updateVehicleAccident(final String vehicleVin,
			final VehicleAccidentDTO vehicleAccidentDto) throws BadRequestException, ResourceNotFoundException {
		if (vehicleVin != vehicleAccidentDto.getVehicleVin()) {
			throw new BadRequestException("Cannot change the vehicle VIN of a vehicle service.");
		}

		final Integer vehicleAccidentId = vehicleAccidentDto.getId();

		final VehicleAccidentEntity vehicleAccidentEntity = vehicleAccidentRepository.findById(vehicleAccidentId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE_SERVICE,
						"Vehicle service with id '" + vehicleAccidentId + "' does not exist."));

		vehicleAccidentEntity.setDateOfAccident(vehicleAccidentDto.getDateOfAccident());
		vehicleAccidentEntity.setDamagePriceEvaluation(vehicleAccidentDto.getDamagePriceEvaluation());

		final VehicleAccidentEntity savedEntity = vehicleAccidentRepository.save(vehicleAccidentEntity);

		return vehicleAccidentMapper.toDto(savedEntity);
	}

	// DELETE
	public void deleteVehicleAccident(final String vehicleVin, final Integer vehicleAccidentId)
			throws ResourceNotFoundException, BadRequestException {
		final VehicleAccidentEntity vehicleAccidentEntity = vehicleAccidentRepository.findById(vehicleAccidentId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE_SERVICE,
						"Vehicle accident with id '" + vehicleAccidentId + "' does not exist."));

		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vehicleVin + "' does not exist."));

		Boolean vehicleAccidentIdMatches = false;

		for (VehicleAccidentEntity vse : vehicleEntity.getAccidents()) {
			if (vse.getId() == vehicleAccidentId) {
				vehicleAccidentIdMatches = true;
			}
		}

		if (vehicleAccidentIdMatches) {

			vehicleAccidentRepository.deleteById(vehicleAccidentEntity.getId());
		} else {
			throw new BadRequestException("The provided VIN and vehicle accident id do not match.");
		}
	}

}
