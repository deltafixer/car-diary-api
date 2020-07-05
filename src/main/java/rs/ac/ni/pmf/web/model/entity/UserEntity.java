package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.pmf.web.model.entity.UserEnums.UserType;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
@NoArgsConstructor
@AllArgsConstructor
// COMMENT: When a class extends this one, let a table be generated but only of subclass-specific fields
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String username;

	@Column(name = "user_type")
	private UserType userType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	private VehicleEntity vehicle;

}
