package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@StaticMetamodel(ServiceUserEntity.class)
public class ServiceUserEntity_ extends UserEntity_ {

	public static volatile SingularAttribute<ServiceUserEntity, String> name;
	public static volatile SingularAttribute<ServiceUserEntity, ServiceType> serviceType;
	public static volatile ListAttribute<ServiceUserEntity, VehicleServiceEntity> services;

}
