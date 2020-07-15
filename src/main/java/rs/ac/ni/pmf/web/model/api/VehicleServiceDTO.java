package rs.ac.ni.pmf.web.model.api;

import java.sql.Date;

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
@ApiModel(description = "Data transfer object representing a vehicle service.")
public class VehicleServiceDTO {

	@ApiModelProperty(value = "Unique identifier.", required = true)
	private Integer id;
	@ApiModelProperty(value = "VIN.", required = true, example = "4T1BF1FK0DU246563")
	private String vehicleVin;
	@ApiModelProperty(value = "Date when the service was taken.", required = true)
	private Date dateTaken;
	@ApiModelProperty(value = "Price of the sevice.", required = true, example = "500.00")
	private Float price;
	@ApiModelProperty(value = "Details/report of the service.", required = true)
	private String serviceDetails;

}
