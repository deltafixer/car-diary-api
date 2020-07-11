package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VehicleAccidentEntity.class)
public class VehicleAccidentEntity_ {

	public static volatile SingularAttribute<VehicleAccidentEntity, Integer> id;
	public static volatile SingularAttribute<VehicleAccidentEntity, VehicleEntity> vehicle;
	public static volatile SingularAttribute<VehicleAccidentEntity, Date> dateOfAccident;
	public static volatile SingularAttribute<VehicleAccidentEntity, Float> damagePriceEvaluation;

}
