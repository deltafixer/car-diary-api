package rs.ac.ni.pmf.web.service;

import java.util.Set;
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
import rs.ac.ni.pmf.web.model.PersonUserSearchOptions;
import rs.ac.ni.pmf.web.model.api.PersonUserDTO;
import rs.ac.ni.pmf.web.model.api.VehicleDTO;
import rs.ac.ni.pmf.web.model.entity.PersonUserEntity;
import rs.ac.ni.pmf.web.model.mapper.PersonUserMapper;
import rs.ac.ni.pmf.web.model.mapper.VehicleMapper;
import rs.ac.ni.pmf.web.repository.PersonUserRepository;
import rs.ac.ni.pmf.web.repository.specification.PersonUserSearchSpecification;

@Service
@RequiredArgsConstructor
public class PersonUserService {

	private static final Integer DEFAULT_PAGE_SIZE = 17;

	private final PersonUserRepository personUserRepository;
	private final PersonUserMapper personUserMapper;
	private final VehicleMapper vehicleMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public Page<PersonUserDTO> getAllPersonUsers(final PersonUserSearchOptions searchOptions) {

		final PageRequest pageRequest;

		if (searchOptions.getPage() != null) {
			pageRequest = PageRequest.of(searchOptions.getPage(),
					searchOptions.getSize() != null ? searchOptions.getSize() : DEFAULT_PAGE_SIZE);
		} else {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}

		return personUserRepository.findAll(new PersonUserSearchSpecification(searchOptions), pageRequest)
				.map(personUserMapper::toDto);
	}

	public PersonUserDTO getPersonUser(final String username) throws ResourceNotFoundException {
		final PersonUserEntity personUserEntity = personUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PERSON_USER,
						"(Person) user with username '" + username + "' does not exist."));

		return personUserMapper.toDto(personUserEntity);
	}

	public Set<VehicleDTO> getPersonUserVehicles(final String username) throws ResourceNotFoundException {
		final PersonUserEntity personUserEntity = personUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PERSON_USER,
						"Person with username '" + username + "' does not exist."));

		return personUserEntity.getVehicles().stream().map(vehicleMapper::toDto).collect(Collectors.toSet());
	}

	public PersonUserDTO addPersonUser(final PersonUserDTO personUserDto) throws DuplicateResourceException {
		final String username = personUserDto.getUsername();

		if (personUserRepository.existsById(username)) {
			throw new DuplicateResourceException(ResourceType.PERSON_USER,
					"Person with username '" + username + "' already exists.");
		}

		final PersonUserEntity personUserEntity = personUserMapper.toEntity(personUserDto);

		PersonUserEntity savedEntity = personUserRepository.save(personUserEntity);

		return personUserMapper.toDto(savedEntity);
	}

	public PersonUserDTO updatePersonUser(final String username, final PersonUserDTO personUserDto)
			throws BadRequestException, ResourceNotFoundException {

		if (personUserDto.getUsername() != null && username != personUserDto.getUsername()) {
			throw new BadRequestException("Cannot change the id of a person.");
		}

		final PersonUserEntity personUserEntity = personUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PERSON_USER,
						"Person with username '" + username + "' does not exist."));

		personUserEntity.setFirstName(personUserDto.getFirstName());
		personUserEntity.setLastName(personUserDto.getLastName());

		final PersonUserEntity savedEntity = personUserRepository.save(personUserEntity);

		return personUserMapper.toDto(savedEntity);
	}

	public void deletePersonUser(final String username) throws ResourceNotFoundException {
		if (!personUserRepository.existsById(username)) {
			throw new ResourceNotFoundException(ResourceType.PERSON_USER,
					"Person with username '" + username + "' does not exist.");
		}

		personUserRepository.deleteById(username);
	}

}
