package rs.ac.ni.pmf.web.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private String username;

	@Column
	private String password;

	@Column(name = "user_type")
	private UserType userType;

	// COMMENT:
	// https://thorben-janssen.com/best-practices-for-many-to-many-associations-with-hibernate-and-jpa/
	// https://www.baeldung.com/jpa-many-to-many
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "user_vehicle", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "vin"))
	private Set<VehicleEntity> vehicles = new HashSet<>();

}
