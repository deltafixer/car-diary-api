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
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;

@RequiredArgsConstructor
public class SaleListingSuggestionScoreSpecification implements Specification<SaleListingEntity> {

	private static final long serialVersionUID = 1L;

	private final Integer saleListingId;

	@Override
	public Predicate toPredicate(Root<SaleListingEntity> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		final Join<SaleListingEntity, VehicleEntity> saleListingVehicleJoin = root.join(SaleListingEntity_.vehicle,
				JoinType.LEFT);

		final Path<Float> price = saleListingVehicleJoin.get("price");
		
		System.out.println(price);
		
		return null;
	}

}