package rs.ac.ni.pmf.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.mapper.VehicleMapper;
import rs.ac.ni.pmf.web.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService {
	private final VehicleRepository vehicleRepository;
	private final VehicleMapper vehicleMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public VehicleDTO getVehicle(final String vin) throws ResourceNotFoundException {
		final VehicleEntity vehicleEntity = vehicleRepository.findById(vin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vin + "' does not exist."));
		return vehicleMapper.toDto(vehicleEntity);
	}

	public VehicleDTO addVehicle(final VehicleDTO VehicleDTO) throws DuplicateResourceException {
		final String vin = VehicleDTO.getVin();

		if (vehicleRepository.existsById(vin)) {
			throw new DuplicateResourceException(ResourceType.VEHICLE, "Vehicle '" + vin + "' already exists.");
		}

		final VehicleEntity vehicleEntity = vehicleMapper.toEntity(VehicleDTO);
		VehicleEntity savedEntity = vehicleRepository.save(vehicleEntity);
		return vehicleMapper.toDto(savedEntity);
	}

	public void deleteVehicle(final String vin) throws ResourceNotFoundException {
		if (!vehicleRepository.existsById(vin)) {
			throw new ResourceNotFoundException(ResourceType.VEHICLE, "Vehicle '" + vin + "' not found.");
		}

		vehicleRepository.deleteById(vin);
	}

}
