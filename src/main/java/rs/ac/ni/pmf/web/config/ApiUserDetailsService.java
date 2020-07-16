package rs.ac.ni.pmf.web.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.PersonUserEntity;
import rs.ac.ni.pmf.web.model.entity.ServiceUserEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;
import rs.ac.ni.pmf.web.model.entity.UserEnums.Role;
import rs.ac.ni.pmf.web.repository.PersonUserRepository;
import rs.ac.ni.pmf.web.repository.ServiceUserRepository;

@Service
@RequiredArgsConstructor
public class ApiUserDetailsService implements UserDetailsService {

	private final PersonUserRepository personUserRepository;
	private final ServiceUserRepository serviceUserRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		final Optional<PersonUserEntity> person = personUserRepository.findById(username);

		if (person.isPresent()) {
			return new UserDetailsImpl(person.get());
		} else {
			final ServiceUserEntity service = serviceUserRepository.findById(username).orElseThrow(
					() -> new UsernameNotFoundException("User with username '" + username + "' does not exist."));
			return new UserDetailsImpl(service);
		}
	}

	@RequiredArgsConstructor
	private final class UserDetailsImpl implements UserDetails {
		private static final long serialVersionUID = 1L;
		private static final String ROLE_PREFIX = "ROLE_";

		private final UserEntity userEntity;

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> authorities = new ArrayList<>();

			if (userEntity.getRole() == Role.ADMIN) {
				authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN"));
			}

			return authorities;
		}

		@Override
		public String getPassword() {
			return userEntity.getPassword();
		}

		@Override
		public String getUsername() {
			return userEntity.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
}