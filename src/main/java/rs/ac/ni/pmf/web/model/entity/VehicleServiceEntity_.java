package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VehicleServiceEntity.class)
public class VehicleServiceEntity_ {
	public static volatile SingularAttribute<VehicleServiceEntity, Integer> id;
	public static volatile SingularAttribute<VehicleServiceEntity, VehicleEntity> vehicle;
	public static volatile SingularAttribute<VehicleServiceEntity, Date> dateTaken;
	public static volatile SingularAttribute<VehicleServiceEntity, Float> price;
	public static volatile SingularAttribute<VehicleServiceEntity, String> serviceDetails;
}
