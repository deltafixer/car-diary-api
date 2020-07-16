package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import rs.ac.ni.pmf.web.model.entity.UserEnums.Role;
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
	@Column(nullable = false, unique = true, length = 30)
	private String username;

	@Column(length = 50, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", length = 20, columnDefinition = "varchar(20) default 'PERSON'")
	private UserType userType;
	
	@Column(nullable = false, columnDefinition = "varchar(6) default 'USER'")
	private Role role;

}
