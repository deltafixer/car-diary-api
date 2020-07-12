package rs.ac.ni.pmf.web.model.api;

import java.sql.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VehicleServiceDTO {

	private Integer id;
	private String vehicleVin;
	private Date dateTaken;
	private Float price;
	private String serviceDetails;

}
