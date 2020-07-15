package rs.ac.ni.pmf.web.repository.specification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.SaleListingSearchOptions;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity_;

@RequiredArgsConstructor
public class SaleListingSearchSpecification implements Specification<SaleListingEntity> {

	private static final long serialVersionUID = 1L;

	private final SaleListingSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<SaleListingEntity> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		final Path<Float> price = root.get(SaleListingEntity_.price);
		final Path<Date> dateAdded = root.get(SaleListingEntity_.dateAdded);

		final Float minPriceFilter = searchOptions.getMinPrice();
		final Float maxPriceFilter = searchOptions.getMaxPrice();
		final Date fromDateFilter = searchOptions.getFromDate();
		final Date toDateFilter = searchOptions.getToDate();

		if (minPriceFilter != null) {
			predicates.add(criteriaBuilder.ge(price, minPriceFilter));
		}

		if (maxPriceFilter != null) {
			predicates.add(criteriaBuilder.le(price, maxPriceFilter));
		}

		if (fromDateFilter != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(dateAdded, fromDateFilter));
		}

		if (toDateFilter != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(dateAdded, toDateFilter));
		}

		query.orderBy(criteriaBuilder.asc(price), criteriaBuilder.desc(dateAdded));

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
