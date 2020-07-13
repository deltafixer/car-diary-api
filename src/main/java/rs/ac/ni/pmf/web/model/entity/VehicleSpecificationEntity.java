package rs.ac.ni.pmf.web.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;

@Data
@Builder
@Entity
@Table(name = "vehicle_specification")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSpecificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Float price;

	@Enumerated(EnumType.STRING)
	@Column(name = "vahicle_condition", length = 30, columnDefinition = "varchar(30) default 'USED'")
	private Condition vehicleCondition;

	@Column(nullable = false)
	private Date makeYear;

	@Enumerated(EnumType.STRING)
	@Column(name = "body_style", length = 20, nullable = false)
	private BodyStyle bodyStyle;

	@Enumerated(EnumType.STRING)
	@Column(name = "drive_type", length = 10, nullable = false)
	private DriveType driveType;

	@Column(nullable = false)
	private Float kilometrage;

	@Enumerated(EnumType.STRING)
	@Column(name = "fuel_type", length = 20, nullable = false)
	private FuelType fuelType;

	@Column(name = "engine_volume", nullable = false)
	private Integer engineVolume;

	@Column(name = "engine_power_kw", nullable = false)
	private Integer enginePowerKW;

	@Enumerated(EnumType.STRING)
	@Column(name = "engine_emission_type", length = 5, nullable = false)
	private EngineEmissionType engineEmissionType;

	@Enumerated(EnumType.STRING)
	@Column(name = "gearbox_type", length = 10, nullable = false)
	private GearboxType gearboxType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_vin", nullable = false)
	private VehicleEntity vehicle;

}
