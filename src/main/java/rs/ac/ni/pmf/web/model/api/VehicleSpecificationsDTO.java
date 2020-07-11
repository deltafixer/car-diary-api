package rs.ac.ni.pmf.web.model.api;

import java.sql.Date;

import lombok.AccessLevel;
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
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VehicleSpecificationsDTO {

	private Integer id;
	private Float price;
	private Condition vehicleCondition;
	private Date makeYear;
	private BodyStyle bodyStyle;
	private DriveType driveType;
	private Float kilometrage;
	private FuelType fuelType;
	private Integer engineVolume;
	private Integer enginePowerKW;
	private EngineEmissionType engineEmissionType;
	private GearboxType gearboxType;

}
