package rs.ac.ni.pmf.web.model.api;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SaleListingDTO {

	private Integer id;
	private String vehicleVin;
	private Float price;
	private Date dateAdded;

}
