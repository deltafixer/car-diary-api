package rs.ac.ni.pmf.web.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.PersonUserSearchOptions;
import rs.ac.ni.pmf.web.model.entity.PersonUserEntity;
import rs.ac.ni.pmf.web.model.entity.PersonUserEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity_;

@RequiredArgsConstructor
public class PersonUserSearchSpecification implements Specification<PersonUserEntity> {

	private static final long serialVersionUID = 1L;

	private final PersonUserSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<PersonUserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		final Path<String> username = root.get(PersonUserEntity_.username);
		final Path<String> firstName = root.get(PersonUserEntity_.firstName);
		final Path<String> lastName = root.get(PersonUserEntity_.lastName);

		final String firstNameFilter = searchOptions.getFirstName();

		if (firstNameFilter != null && !firstNameFilter.trim().isEmpty()) {
			predicates.add(
					criteriaBuilder.like(criteriaBuilder.lower(firstName), "%" + firstNameFilter.toLowerCase() + "%"));
		}

		final String lastNameFilter = searchOptions.getLastName();

		if (lastNameFilter != null && !lastNameFilter.trim().isEmpty()) {
			predicates.add(
					criteriaBuilder.like(criteriaBuilder.lower(lastName), "%" + lastNameFilter.toLowerCase() + "%"));
		}

		// COMMENT: not sure if magic, but I think this works
		if (searchOptions.getMinVehicleCount() != null) {

			final Join<PersonUserEntity, VehicleEntity> personUserVehicleJoin = root.join(PersonUserEntity_.vehicles,
					JoinType.LEFT);

			final Path<String> vehicleVin = personUserVehicleJoin.get(VehicleEntity_.vin);

			query.groupBy(username);
			query.having(criteriaBuilder.ge(criteriaBuilder.count(vehicleVin), searchOptions.getMinVehicleCount()));
		}

		if (searchOptions.getMaxVehicleCount() != null) {

			final Join<PersonUserEntity, VehicleEntity> personUserVehicleJoin = root.join(PersonUserEntity_.vehicles,
					JoinType.LEFT);

			final Path<String> vehicleVin = personUserVehicleJoin.get(VehicleEntity_.vin);

			query.groupBy(username);
			query.having(criteriaBuilder.le(criteriaBuilder.count(vehicleVin), searchOptions.getMaxVehicleCount()));
		}

		query.orderBy(criteriaBuilder.asc(firstName), criteriaBuilder.asc(lastName));

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
