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
@ApiModel(description = "Data transfer object representing a vehicle sale listing.")
public class SaleListingDTO {

	@ApiModelProperty(value = "Unique identifier.", required = true)
	private Integer id;
	@ApiModelProperty(value = "Vehicle VIN.", required = true, example = "4T1BF1FK0DU246563")
	private String vehicleVin;
	@ApiModelProperty(value = "Price of the vehicle.", required = true, example = "5999.99")
	private Float price;
	@ApiModelProperty(value = "Date when the sale listing was posted.", required = true)
	private Date dateAdded;

}
