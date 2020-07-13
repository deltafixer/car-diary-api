package rs.ac.ni.pmf.web.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.VehicleSearchOptions;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.mapper.PersonUserMapper;
import rs.ac.ni.pmf.web.model.mapper.VehicleMapper;
import rs.ac.ni.pmf.web.repository.VehicleRepository;
import rs.ac.ni.pmf.web.repository.specification.VehicleSearchSpecification;

@Service
@RequiredArgsConstructor
public class VehicleService {

	private static final Integer DEFAULT_PAGE_SIZE = 10;

	private final VehicleRepository vehicleRepository;
	private final VehicleMapper vehicleMapper;
	private final PersonUserMapper personUserMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public Page<VehicleDTO> getAllVehicles(final VehicleSearchOptions searchOptions) {

		final PageRequest pageRequest;

		if (searchOptions.getPage() != null) {
			pageRequest = PageRequest.of(searchOptions.getPage(),
					searchOptions.getSize() != null ? searchOptions.getSize() : DEFAULT_PAGE_SIZE);
		} else {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}

		return vehicleRepository.findAll(new VehicleSearchSpecification(searchOptions), pageRequest)
				.map(vehicleMapper::toDto);
	}

	public VehicleDTO getVehicle(final String vin) throws ResourceNotFoundException {
		final VehicleEntity vehicleEntity = vehicleRepository.findById(vin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vin + "' does not exist."));
		return vehicleMapper.toDto(vehicleEntity);
	}

	public Set<PersonUserDTO> getVehicleOwners(String vehicleVin) throws ResourceNotFoundException {
		final VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleVin)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.VEHICLE,
						"Vehicle with VIN '" + vehicleVin + "' does not exist."));

		return vehicleEntity.getOwners().stream().map(personUserMapper::toDto).collect(Collectors.toSet());
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
