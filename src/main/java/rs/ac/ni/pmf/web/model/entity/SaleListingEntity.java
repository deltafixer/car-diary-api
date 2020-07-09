package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "sale_listing")
@NoArgsConstructor
@AllArgsConstructor
public class SaleListingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	private VehicleEntity vehicle;

	private Float price;

	@Column(name = "date_added")
	private Date dateAdded;

	@Builder.Default
	@Column(name = "suggestion_score")
	private Range<Integer> suggestionScore = Range.between(1, 100);
}
