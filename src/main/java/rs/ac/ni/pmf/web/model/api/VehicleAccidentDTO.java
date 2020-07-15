package rs.ac.ni.pmf.web.model.api;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ApiModel(description = "Data transfer object representing a vehicle accident.")
public class VehicleAccidentDTO {

	@ApiModelProperty(value = "Unique identifier.", required = true)
	private Integer id;
	@ApiModelProperty(value = "VIN.", required = true, example = "4T1BF1FK0DU246563")
	private String vehicleVin;
	@ApiModelProperty(value = "Date when the accident occured.", required = true)
	private Date dateOfAccident;
	@ApiModelProperty(value = "Damage cost to fix evaluation.", required = true, example = "600.00")
	private Float damagePriceEvaluation;

}
