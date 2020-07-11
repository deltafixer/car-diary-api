package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;

@StaticMetamodel(VehicleSpecificationsEntity.class)
public class VehicleSpecificationsEntity_ {

	public static volatile SingularAttribute<VehicleSpecificationsEntity, Integer> id;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, Float> price;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, Condition> vehicleCondition;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, Date> makeYear;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, BodyStyle> bodyStyle;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, DriveType> driveType;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, Float> kilometrage;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, FuelType> fuelType;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, Integer> engineVolume;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, Integer> enginePowerKW;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, EngineEmissionType> engineEmissionType;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, GearboxType> gearboxType;
	public static volatile SingularAttribute<VehicleSpecificationsEntity, VehicleEntity> vehicle;

}
