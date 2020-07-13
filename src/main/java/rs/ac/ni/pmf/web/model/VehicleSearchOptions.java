package rs.ac.ni.pmf.web.model;

import java.sql.Date;

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

	private Float priceFrom;
	private Float priceTo;
	private Condition conditionFilter;
	private Date makeYearFilter;
	private BodyStyle bodyStyleFilter;
	private DriveType driveTypeFilter;
	private Float kilometrageFrom;
	private Float kilometrageTo;
	private FuelType fuelType;
	private Integer engineVolume;
	private Integer enginePowerKW;
	private EngineEmissionType engineEmissionTypeFilter;
	private GearboxType gearboxTypeFilter;
	private Integer page;
	private Integer size;
	
}
