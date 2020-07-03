package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@Entity
@Table(name = "service_user")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUserEntity extends UserEntity {

	@Column(name = "service_type")
	private ServiceType serviceType;
}
