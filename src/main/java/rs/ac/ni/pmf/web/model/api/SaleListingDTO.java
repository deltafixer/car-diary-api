package rs.ac.ni.pmf.web.model.api;

import java.util.Date;

import org.apache.commons.lang3.Range;

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
	private Float price;
	private Date dateAdded;
	private Range<Integer> suggestionScore;
}
