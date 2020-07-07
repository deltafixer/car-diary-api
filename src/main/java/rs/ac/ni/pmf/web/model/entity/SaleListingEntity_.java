package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.apache.commons.lang3.Range;

@StaticMetamodel(SaleListingEntity.class)
public class SaleListingEntity_ {
	public static volatile SingularAttribute<SaleListingEntity, Integer> id;
	public static volatile SingularAttribute<SaleListingEntity, VehicleEntity> vehicle;
	public static volatile SingularAttribute<SaleListingEntity, Float> price;
	public static volatile SingularAttribute<SaleListingEntity, Date> dateAdded;
	public static volatile SingularAttribute<SaleListingEntity, Range<Integer>> suggestionScore;
}
