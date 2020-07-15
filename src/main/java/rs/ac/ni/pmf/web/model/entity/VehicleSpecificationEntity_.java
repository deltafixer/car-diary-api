package rs.ac.ni.pmf.web.model.entity;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;

@StaticMetamodel(VehicleSpecificationEntity.class)
public class VehicleSpecificationEntity_ {

	public static volatile SingularAttribute<VehicleSpecificationEntity, Integer> id;
	public static volatile SingularAttribute<VehicleSpecificationEntity, Float> price;
	public static volatile SingularAttribute<VehicleSpecificationEntity, Condition> vehicleCondition;
	public static volatile SingularAttribute<VehicleSpecificationEntity, Date> makeYear;
	public static volatile SingularAttribute<VehicleSpecificationEntity, BodyStyle> bodyStyle;
	public static volatile SingularAttribute<VehicleSpecificationEntity, DriveType> driveType;
	public static volatile SingularAttribute<VehicleSpecificationEntity, Float> kilometrage;
	public static volatile SingularAttribute<VehicleSpecificationEntity, FuelType> fuelType;
	public static volatile SingularAttribute<VehicleSpecificationEntity, Integer> engineVolume;
	public static volatile SingularAttribute<VehicleSpecificationEntity, Integer> enginePowerKW;
	public static volatile SingularAttribute<VehicleSpecificationEntity, EngineEmissionType> engineEmissionType;
	public static volatile SingularAttribute<VehicleSpecificationEntity, GearboxType> gearboxType;
	public static volatile SingularAttribute<VehicleSpecificationEntity, VehicleEntity> vehicle;

}
