package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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

}
