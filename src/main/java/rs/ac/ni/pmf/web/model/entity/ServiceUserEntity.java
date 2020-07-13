package rs.ac.ni.pmf.web.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.pmf.web.model.entity.UserEnums.ServiceType;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "service_user")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUserEntity extends UserEntity {

	@Column(nullable = false, length = 30)
	private String name;

	@Column(name = "service_type", columnDefinition = "varchar(20) default 'AUTHORIZED'")
	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;

	@Builder.Default
	@OneToMany(mappedBy = "servicedBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VehicleServiceEntity> services = new ArrayList<>();

}
