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
import rs.ac.ni.pmf.web.model.ServiceUserSearchOptions;
import rs.ac.ni.pmf.web.model.entity.ServiceUserEntity;
import rs.ac.ni.pmf.web.model.entity.ServiceUserEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity_;

@RequiredArgsConstructor
public class ServiceUserSearchSpecification implements Specification<ServiceUserEntity> {

	private static final long serialVersionUID = 1L;

	private final ServiceUserSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<ServiceUserEntity> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		final String nameFilter = searchOptions.getName();

		if (nameFilter != null) {
			final Path<String> name = root.get(ServiceUserEntity_.name);

			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(name), "%" + nameFilter.toLowerCase() + "%"));
		}

		final Path<String> username = root.get(ServiceUserEntity_.username);

		final Integer minServicesCount = searchOptions.getMinServicesCount();
		final Integer maxServicesCount = searchOptions.getMaxServicesCount();

		if (minServicesCount != null || maxServicesCount != null) {

			final Join<ServiceUserEntity, VehicleServiceEntity> serviceVehicleServiceJoin = root
					.join(ServiceUserEntity_.services, JoinType.LEFT);

			final Path<Integer> serviceId = serviceVehicleServiceJoin.get(VehicleServiceEntity_.id);

			if (minServicesCount != null) {
				query.groupBy(username);
				query.having(criteriaBuilder.ge(criteriaBuilder.count(serviceId), minServicesCount));
			}

			if (maxServicesCount != null) {
				query.groupBy(username);
				query.having(criteriaBuilder.le(criteriaBuilder.count(serviceId), maxServicesCount));
			}
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
