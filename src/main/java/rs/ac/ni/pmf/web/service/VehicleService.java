package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.VehicleAccidentDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.mapper.VehicleAccidentMapper;
import rs.ac.ni.pmf.web.model.mapper.VehicleMapper;
import rs.ac.ni.pmf.web.repository.VehicleAccidentRepository;
import rs.ac.ni.pmf.web.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService {

	private final VehicleRepository vehicleRepository;
	private final VehicleAccidentRepository vehicleAccidentRepository;
	private final VehicleMapper vehicleMapper;
	private final VehicleAccidentMapper vehicleAccidentMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public VehicleDTO getVehicle(final String vin) throws ResourceNotFoundException {
		final VehicleEntity vehicleEntity = vehicleRepository.findById(vin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vin + "' does not exist."));
		return vehicleMapper.toDto(vehicleEntity);
	}

	public List<VehicleAccidentDTO> getVehicleAccidents(final String vin) throws ResourceNotFoundException {
		final VehicleEntity vehicleEntity = vehicleRepository.findById(vin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vin + "' does not exist."));

		return vehicleAccidentRepository.findByVehicleVin(vehicleEntity.getVin()).stream()
				.map(vehicleAccidentMapper::toDto).collect(Collectors.toList());
	}

	public VehicleDTO addVehicle(final VehicleDTO vehicleDTO) throws DuplicateResourceException {
		final String vin = vehicleDTO.getVin();

		if (vehicleRepository.existsById(vin)) {
			throw new DuplicateResourceException(ResourceType.VEHICLE, "Vehicle '" + vin + "' already exists.");
		}

		final VehicleEntity vehicleEntity = vehicleMapper.toEntity(vehicleDTO);
		VehicleEntity savedEntity = vehicleRepository.save(vehicleEntity);
		return vehicleMapper.toDto(savedEntity);
	}

	public void deleteVehicle(final String vin) throws ResourceNotFoundException {
		if (!vehicleRepository.existsById(vin)) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE, "Vehicle '" + vin + "' does not exist.");
		}

		vehicleRepository.deleteById(vin);
	}

}
