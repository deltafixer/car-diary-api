package rs.ac.ni.pmf.web.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Make;
import rs.ac.ni.pmf.web.model.entity.VehicleEnums.Model;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ApiModel(description = "Data transfer object representing a vehicle.")
public class VehicleDTO {

	@ApiModelProperty(value = "VIN.", required = true, example = "4T1BF1FK0DU246563")
	private String vin;
	@ApiModelProperty(value = "Vehicle's make.", required = true, example = "OPEL")
	private Make make;
	@ApiModelProperty(value = "Vehicle's model.", required = true, example = "ASTRA J GTC")
	private Model model;

}
