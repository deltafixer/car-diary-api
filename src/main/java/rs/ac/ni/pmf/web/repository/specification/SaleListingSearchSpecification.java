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
		final Path<Integer> suggestionScore = root.get(SaleListingEntity_.suggestionScore);

		final Float minPriceFilter = searchOptions.getMinPrice();
		final Float maxPriceFilter = searchOptions.getMaxPrice();
		final Date fromDateFilter = searchOptions.getFromDate();
		final Date toDateFilter = searchOptions.getToDate();
		final Integer minSuggestionScoreFilter = searchOptions.getMinSuggestionScore();
		final Integer maxSuggestionScoreFilter = searchOptions.getMaxSuggestionScore();

		if (minPriceFilter != null) {
			query.having(criteriaBuilder.ge(price, minPriceFilter));
		}

		if (maxPriceFilter != null) {
			query.having(criteriaBuilder.le(price, maxPriceFilter));
		}

		if (fromDateFilter != null) {
			query.having(criteriaBuilder.lessThanOrEqualTo(dateAdded, fromDateFilter));
		}

		if (toDateFilter != null) {
			query.having(criteriaBuilder.greaterThanOrEqualTo(dateAdded, toDateFilter));
		}

		if (minSuggestionScoreFilter != null) {
			query.having(criteriaBuilder.ge(suggestionScore, minSuggestionScoreFilter));
		}

		if (maxSuggestionScoreFilter != null) {
			query.having(criteriaBuilder.le(suggestionScore, maxSuggestionScoreFilter));
		}

		query.orderBy(criteriaBuilder.desc(suggestionScore), criteriaBuilder.asc(price),
				criteriaBuilder.desc(dateAdded));

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
