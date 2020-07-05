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
public class VehicleAccidentDTO {
	private Integer id;
	private Date dateOfAccident;
	private float damagePriceEvaluation;
}
