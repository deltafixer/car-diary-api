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
@Table(name = "vehicle_specifications")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSpecificationsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Float price;

	@Enumerated(EnumType.STRING)
	@Column(name = "vahicle_condition")
	private Condition vehicleCondition;

	private Date makeYear;

	@Enumerated(EnumType.STRING)
	@Column(name = "body_style")
	private BodyStyle bodyStyle;

	@Enumerated(EnumType.STRING)
	@Column(name = "drive_type")
	private DriveType driveType;

	private Float kilometrage;

	@Enumerated(EnumType.STRING)
	@Column(name = "fuel_type")
	private FuelType fuelType;

	@Column(name = "engine_volume")
	private Integer engineVolume;

	@Column(name = "engine_power_kw")
	private Integer enginePowerKW;

	@Enumerated(EnumType.STRING)
	@Column(name = "engine_emission_type")
	private EngineEmissionType engineEmissionType;

	@Enumerated(EnumType.STRING)
	@Column(name = "gearbox_type")
	private GearboxType gearboxType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	private VehicleEntity vehicle;
}
