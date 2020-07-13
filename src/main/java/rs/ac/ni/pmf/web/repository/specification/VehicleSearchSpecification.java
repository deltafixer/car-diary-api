package rs.ac.ni.pmf.web.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.PersonUserSearchOptions;
import rs.ac.ni.pmf.web.model.VehicleSearchOptions;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;

@RequiredArgsConstructor
public class VehicleSearchSpecification implements Specification<VehicleEntity> {

	private static final long serialVersionUID = 1L;

	private final VehicleSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<VehicleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return null;
	}

}
