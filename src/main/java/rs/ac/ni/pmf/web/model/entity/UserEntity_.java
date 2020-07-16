package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.UserEnums.Role;
import rs.ac.ni.pmf.web.model.entity.UserEnums.UserType;


@StaticMetamodel(UserEntity.class)
public class UserEntity_ {

//	COMMENT: volatile = stored in main memory
	public static volatile SingularAttribute<UserEntity, String> username;
	public static volatile SingularAttribute<UserEntity, String> password;
	public static volatile SingularAttribute<UserEntity, UserType> userType;
	public static volatile SingularAttribute<UserEntity, Role> role;
	public static volatile SetAttribute<UserEntity, VehicleEntity> vehicles;

}
