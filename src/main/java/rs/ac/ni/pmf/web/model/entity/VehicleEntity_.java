package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Make;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Model;

@StaticMetamodel(VehicleEntity.class)
public class VehicleEntity_ {
	public static volatile SingularAttribute<VehicleEntity, String> vin;
	public static volatile SingularAttribute<VehicleEntity, Make> make;
	public static volatile SingularAttribute<VehicleEntity, Model> model;
	public static volatile SingularAttribute<VehicleEntity, VehicleSpecificationsEntity> specifications;
	public static volatile ListAttribute<VehicleEntity, VehicleServiceEntity> services;
	public static volatile ListAttribute<VehicleEntity, VehicleAccidentEntity> accidents;
	public static volatile ListAttribute<VehicleEntity, UserEntity> owners;
}
