package rs.ac.ni.pmf.web.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Make;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Model;

@Data
@Builder
@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

//	COMMENT: suppose that we use 'Integer' only because of 'CrudRepository'?
	@Id
	private String vin;

	@Enumerated(EnumType.STRING)
	private Make make;

	@Enumerated(EnumType.STRING)
	private Model model;

	@OneToOne(fetch = FetchType.LAZY)
	private VehicleSpecificationsEntity specifications;

	@Builder.Default
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VehicleServiceEntity> services = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VehicleAccidentEntity> accidents = new ArrayList<>();

	@Builder.Default
	@ManyToMany(mappedBy = "vehicles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserEntity> owners = new ArrayList<>();

}
