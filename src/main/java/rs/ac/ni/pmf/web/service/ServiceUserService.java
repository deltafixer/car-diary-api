package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ServiceUserSearchOptions;
import rs.ac.ni.pmf.web.model.api.ServiceUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleServiceDTO;
import rs.ac.ni.pmf.web.model.entity.ServiceUserEntity;
import rs.ac.ni.pmf.web.model.mapper.ServiceUserMapper;
import rs.ac.ni.pmf.web.model.mapper.VehicleServiceMapper;
import rs.ac.ni.pmf.web.repository.ServiceUserRepository;
import rs.ac.ni.pmf.web.repository.specification.ServiceUserSearchSpecification;

@Service
@RequiredArgsConstructor
public class ServiceUserService {

	private static final Integer DEFAULT_PAGE_SIZE = 17;

	private final ServiceUserRepository serviceUserRepository;
	private final ServiceUserMapper serviceUserMapper;
	private final VehicleServiceMapper vehicleServiceMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public Page<ServiceUserDTO> getAllServiceUsers(final ServiceUserSearchOptions searchOptions) {

		final PageRequest pageRequest;

		if (searchOptions.getPage() != null) {
			pageRequest = PageRequest.of(searchOptions.getPage(),
					searchOptions.getSize() != null ? searchOptions.getSize() : DEFAULT_PAGE_SIZE);
		} else {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}

		return serviceUserRepository.findAll(new ServiceUserSearchSpecification(searchOptions), pageRequest)
				.map(serviceUserMapper::toDto);
	}

	public ServiceUserDTO getServiceUser(final String username) throws ResourceNotFoundException {
		final ServiceUserEntity serviceUserEntity = serviceUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.SERVICE_USER,
						"Service with username '" + username + "' does not exist."));

		return serviceUserMapper.toDto(serviceUserEntity);
	}

	public List<VehicleServiceDTO> getServiceUserServices(final String username) throws ResourceNotFoundException {
		final ServiceUserEntity serviceUserEntity = serviceUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PERSON_USER,
						"Service with username '" + username + "' does not exist."));

		return serviceUserEntity.getServices().stream().map(vehicleServiceMapper::toDto).collect(Collectors.toList());
	}

	public ServiceUserDTO addServiceUser(final ServiceUserDTO serviceUserDto) throws DuplicateResourceException {
		final String username = serviceUserDto.getUsername();

		if (serviceUserRepository.existsById(username)) {
			throw new DuplicateResourceException(ResourceType.PERSON_USER,
					"Service with username '" + username + "' already exists.");
		}

		final ServiceUserEntity serviceUserEntity = serviceUserMapper.toEntity(serviceUserDto);

		ServiceUserEntity savedEntity = serviceUserRepository.save(serviceUserEntity);

		return serviceUserMapper.toDto(savedEntity);
	}

	public ServiceUserDTO updateServiceUser(final String username, final ServiceUserDTO serviceUserDto)
			throws BadRequestException, ResourceNotFoundException {

		if (serviceUserDto.getUsername() != null && username != serviceUserDto.getUsername()) {
			throw new BadRequestException("Cannot change the id of a service.");
		}

		final ServiceUserEntity serviceUserEntity = serviceUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PERSON_USER,
						"Service with username '" + username + "' does not exist."));

		serviceUserEntity.setName(serviceUserDto.getName());
		serviceUserEntity.setServiceType(serviceUserDto.getServiceType());

		final ServiceUserEntity savedEntity = serviceUserRepository.save(serviceUserEntity);

		return serviceUserMapper.toDto(savedEntity);
	}

	public void deleteServiceUser(final String username) throws ResourceNotFoundException {
		if (!serviceUserRepository.existsById(username)) {
			throw new ResourceNotFoundException(ResourceType.SERVICE_USER,
					"Service with username '" + username + "' does not exist.");
		}

		serviceUserRepository.deleteById(username);
	}

}
