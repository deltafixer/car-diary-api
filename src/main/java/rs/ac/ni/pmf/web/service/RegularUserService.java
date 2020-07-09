package rs.ac.ni.pmf.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.model.api.RegularUserDTO;
import rs.ac.ni.pmf.web.model.entity.RegularUserEntity;
import rs.ac.ni.pmf.web.model.mapper.RegularUserMapper;
import rs.ac.ni.pmf.web.repository.RegularUserRepository;

@Service
@RequiredArgsConstructor
public class RegularUserService {
	private final RegularUserRepository regularUserRepository;
	private final RegularUserMapper regularUserMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public RegularUserDTO getRegularUser(final String username) throws ResourceNotFoundException {
		final RegularUserEntity regularUserEntity = regularUserRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.REGULAR_USER,
						"(Regular) user with username '" + username + "' does not exist."));

		return regularUserMapper.toDto(regularUserEntity);
	}

	public RegularUserDTO addRegularUser(final RegularUserDTO RegularUserDTO) throws DuplicateResourceException {
		final String username = RegularUserDTO.getUsername();

		if (regularUserRepository.existsById(username)) {
			throw new DuplicateResourceException(ResourceType.REGULAR_USER,
					"(Regular) user '" + username + "' already exists.");
		}

		final RegularUserEntity regularUserEntity = regularUserMapper.toEntity(RegularUserDTO);

		RegularUserEntity savedEntity = regularUserRepository.save(regularUserEntity);

		return regularUserMapper.toDto(savedEntity);
	}

	public void deleteRegularUser(final String username) throws ResourceNotFoundException {
		if (!regularUserRepository.existsById(username)) {
			throw new ResourceNotFoundException(ResourceType.REGULAR_USER,
					"(Regular) user '" + username + "' not found.");
		}

		regularUserRepository.deleteById(username);
	}
}
