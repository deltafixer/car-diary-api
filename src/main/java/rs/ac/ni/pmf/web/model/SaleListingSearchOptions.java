package rs.ac.ni.pmf.web.model;

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SaleListingSearchOptions {


	@ApiModelProperty(value = "Minimum listing price.")
	private Float minPrice;
	@ApiModelProperty(value = "Maximum listing price.")
	private Float maxPrice;
	@ApiModelProperty(value = "Listing posted starting at this date.")
	private Date fromDate;
	@ApiModelProperty(value = "Listing posted ending at this date.")
	private Date toDate;
	@ApiModelProperty(value = "Page number.")
	private Integer page;
	@ApiModelProperty(value = "Page size.")
	private Integer size;

}
