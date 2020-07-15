package rs.ac.ni.pmf.web.model.api;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Data transfer object representing vehicle specification.")
public class VehicleSpecificationDTO {

	@ApiModelProperty(value = "Unique identifier.", required = true)
	private Integer id;
	@ApiModelProperty(value = "Base (factory) price.", required = true)
	private Float price;
	@ApiModelProperty(value = "Condition the vehicle is in.", required = true, example = "USED")
	private Condition vehicleCondition;
	@ApiModelProperty(value = "Vehicle's make year.", required = true)
	private Date makeYear;
	@ApiModelProperty(value = "Vehicle's body style.", required = true, example = "COUPE")
	private BodyStyle bodyStyle;
	@ApiModelProperty(value = "Vehicle's drive type.", required = true, example = "RWD")
	private DriveType driveType;
	@ApiModelProperty(value = "Vehicle's kilometrage.", required = true, example = "152900")
	private Float kilometrage;
	@ApiModelProperty(value = "Vehicle's fuel type.", required = true, example = "PETROL")
	private FuelType fuelType;
	@ApiModelProperty(value = "Vehicle's engine volume.", required = true, example = "1587")
	private Integer engineVolume;
	@ApiModelProperty(value = "Vehicle's engine power in kW.", required = true, example = "132")
	private Integer enginePowerKW;
	@ApiModelProperty(value = "Vehicle's engine emission type.", required = true, example = "EURO_5")
	private EngineEmissionType engineEmissionType;
	@ApiModelProperty(value = "Vehicle's gearbox type.", required = true, example = "MANUAL_6")
	private GearboxType gearboxType;
	@ApiModelProperty(value = "VIN.", required = true, example = "4T1BF1FK0DU246563")
	private String vehicleVin;

}
