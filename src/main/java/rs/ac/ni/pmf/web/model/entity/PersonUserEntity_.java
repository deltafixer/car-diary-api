package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PersonUserEntity.class)
public class PersonUserEntity_ extends UserEntity_ {

	public static volatile SingularAttribute<PersonUserEntity, String> firstName;
	public static volatile SingularAttribute<PersonUserEntity, String> lastName;
	public static volatile SetAttribute<PersonUserEntity, VehicleEntity> vehicles;

}
