package rs.ac.ni.pmf.web.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "person_user")
@NoArgsConstructor
@AllArgsConstructor
public class PersonUserEntity extends UserEntity {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	// COMMENT:
	// https://thorben-janssen.com/best-practices-for-many-to-many-associations-with-hibernate-and-jpa/
	// https://www.baeldung.com/jpa-many-to-many
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "user_vehicle", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "vin"))
	private Set<VehicleEntity> vehicles = new HashSet<>();

}
