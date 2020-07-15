package rs.ac.ni.pmf.web.repository.specification;

import java.sql.Date;
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
import rs.ac.ni.pmf.web.model.VehicleSearchOptions;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleEntity_;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Make;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Model;
import rs.ac.ni.pmf.web.model.entity.VehicleSpecificationEntity;
import rs.ac.ni.pmf.web.model.entity.VehicleSpecificationEntity_;

@RequiredArgsConstructor
public class VehicleSearchSpecification implements Specification<VehicleEntity> {

	private static final long serialVersionUID = 1L;

	private final VehicleSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<VehicleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		final Float priceFromFilter = searchOptions.getPriceFrom();
		final Float priceToFilter = searchOptions.getPriceTo();
		final Condition conditionFilter = searchOptions.getCondition();
		final Date makeYearFilter = searchOptions.getMakeYear();
		final BodyStyle bodyStyleFilter = searchOptions.getBodyStyle();
		final DriveType driveTypeFilter = searchOptions.getDriveType();
		final Float kilometrageFromFilter = searchOptions.getKilometrageFrom();
		final Float kilometrageToFilter = searchOptions.getKilometrageTo();
		final FuelType fuelTypeFilter = searchOptions.getFuelType();
		final Integer engineVolumeFilter = searchOptions.getEngineVolume();
		final Integer enginePowerKWFilter = searchOptions.getEnginePowerKW();
		final EngineEmissionType engineEmissionTypeFilter = searchOptions.getEngineEmissionType();
		final GearboxType gearboxTypeFilter = searchOptions.getGearboxType();

		final Path<Make> make = root.get(VehicleEntity_.make);
		final Path<Model> model = root.get(VehicleEntity_.model);

		if (priceFromFilter != null || priceToFilter != null) {
			final Join<VehicleEntity, SaleListingEntity> vehicleSaleListingJoin = root.join(VehicleEntity_.saleListing,
					JoinType.LEFT);
			final Path<Float> price = vehicleSaleListingJoin.get(SaleListingEntity_.price);

			if (priceFromFilter != null) {
				query.having(criteriaBuilder.ge(price, priceFromFilter));
			}

			if (priceToFilter != null) {
				query.having(criteriaBuilder.le(price, priceToFilter));
			}
		}

		final Join<VehicleEntity, VehicleSpecificationEntity> vehicleSpecificationListingJoin = root
				.join(VehicleEntity_.specification, JoinType.LEFT);

		if (conditionFilter != null) {
			final Path<Condition> condition = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.vehicleCondition);

			query.having(criteriaBuilder.equal(condition, conditionFilter));
		}

		if (makeYearFilter != null) {
			final Path<Date> makeYear = vehicleSpecificationListingJoin.get(VehicleSpecificationEntity_.makeYear);

			query.having(criteriaBuilder.equal(makeYear, makeYearFilter));
		}

		if (bodyStyleFilter != null) {
			final Path<BodyStyle> bodyStyle = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.bodyStyle);

			query.having(criteriaBuilder.equal(bodyStyle, bodyStyleFilter));
		}

		if (driveTypeFilter != null) {
			final Path<DriveType> driveType = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.driveType);

			query.having(criteriaBuilder.equal(driveType, driveTypeFilter));
		}

		if (kilometrageFromFilter != null || kilometrageToFilter != null) {
			final Path<Float> kilometrage = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.kilometrage);

			if (kilometrageFromFilter != null) {
				query.having(criteriaBuilder.ge(kilometrage, kilometrageFromFilter));
			}

			if (kilometrageToFilter != null) {
				query.having(criteriaBuilder.le(kilometrage, kilometrageToFilter));
			}
		}

		if (fuelTypeFilter != null) {
			final Path<FuelType> fuelType = vehicleSpecificationListingJoin.get(VehicleSpecificationEntity_.fuelType);

			query.having(criteriaBuilder.equal(fuelType, fuelTypeFilter));
		}

		if (engineVolumeFilter != null) {
			final Path<Integer> engineVolume = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.engineVolume);

			query.having(criteriaBuilder.equal(engineVolume, engineVolumeFilter));
		}

		if (enginePowerKWFilter != null) {
			final Path<Integer> enginePowerKW = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.enginePowerKW);

			query.having(criteriaBuilder.equal(enginePowerKW, enginePowerKWFilter));
		}

		if (engineEmissionTypeFilter != null) {
			final Path<EngineEmissionType> engineEmissionType = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.engineEmissionType);

			query.having(criteriaBuilder.equal(engineEmissionType, engineEmissionTypeFilter));
		}

		if (gearboxTypeFilter != null) {
			final Path<GearboxType> gearboxType = vehicleSpecificationListingJoin
					.get(VehicleSpecificationEntity_.gearboxType);

			query.having(criteriaBuilder.equal(gearboxType, gearboxTypeFilter));
		}

		query.orderBy(criteriaBuilder.asc(make), criteriaBuilder.asc(model));

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
