package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RegularUserEntity.class)
public class RegularUserEntity_ extends UserEntity_ {
	public static volatile SingularAttribute<RegularUserEntity, String> firstName;
	public static volatile SingularAttribute<RegularUserEntity, String> lastName;
}
