package rs.ac.ni.pmf.web.model;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SaleListingSearchOptions {

	private Float minPrice;
	private Float maxPrice;
	private Date fromDate;
	private Date toDate;
	private Integer page;
	private Integer size;

}
