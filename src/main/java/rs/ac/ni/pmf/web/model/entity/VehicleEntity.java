package rs.ac.ni.pmf.web.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Make;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Model;

@Data
@Builder
@Entity
@Table(name = "vehicle", uniqueConstraints = { @UniqueConstraint(columnNames = "vin") })
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

//	COMMENT: suppose that we use 'Integer' only because of, i.e., 'CrudRepository'?
	@Id
	@Column(nullable = false, length = 17, unique = true)
	private String vin;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Make make;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Model model;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specification_id", nullable = false)
	private VehicleSpecificationEntity specification;

	@Builder.Default
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VehicleServiceEntity> services = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VehicleAccidentEntity> accidents = new ArrayList<>();

	@Builder.Default
	@ManyToMany(mappedBy = "vehicles", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PersonUserEntity> owners = new HashSet<>();

//	@OneToOne
//	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_listing_id", nullable = true)
	private SaleListingEntity saleListing;

}
