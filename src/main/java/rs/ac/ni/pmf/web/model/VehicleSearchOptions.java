package rs.ac.ni.pmf.web.model;

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.BodyStyle;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Condition;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.DriveType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.EngineEmissionType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.FuelType;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.GearboxType;

@Value
@Builder
public class VehicleSearchOptions {

	@ApiModelProperty(value = "If there's a sale listing, minimum price.")
	private Float priceFrom;
	@ApiModelProperty(value = "If there's a sale listing, maximum price.")
	private Float priceTo;
	@ApiModelProperty(value = "Condition of the vehicle.")
	private Condition condition;
	@ApiModelProperty(value = "Vehicle's make year.")
	private Date makeYear;
	@ApiModelProperty(value = "Vehicle's body style.")
	private BodyStyle bodyStyle;
	@ApiModelProperty(value = "Vehicle's drive type.")
	private DriveType driveType;
	@ApiModelProperty(value = "Vehicle with at least as this much kilometers on the clock.")
	private Float kilometrageFrom;
	@ApiModelProperty(value = "Vehicle with at much as this much kilometers on the clock.")
	private Float kilometrageTo;
	@ApiModelProperty(value = "Vehicle's fuel type.")
	private FuelType fuelType;
	@ApiModelProperty(value = "Vehicle's engine volume.")
	private Integer engineVolume;
	@ApiModelProperty(value = "Vehicle's engine power in kW.")
	private Integer enginePowerKW;
	@ApiModelProperty(value = "Vehicle's engine emission type.")
	private EngineEmissionType engineEmissionType;
	@ApiModelProperty(value = "Vehicle's gearbox type.")
	private GearboxType gearboxType;
	@ApiModelProperty(value = "Page number.")
	private Integer page;
	@ApiModelProperty(value = "Page size.")
	private Integer size;

}
