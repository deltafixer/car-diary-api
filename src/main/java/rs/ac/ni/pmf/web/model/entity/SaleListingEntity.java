package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

//	COMMENT: https://www.baeldung.com/jpa-one-to-one
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_vin", nullable = false)
//	@JoinTable(name = "vehicle_sale_listing", joinColumns = {
//			@JoinColumn(name = "sale_listing_id", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "vehicle_vin", referencedColumnName = "vin") })
	private VehicleEntity vehicle;

	@Column(nullable = false)
	private Float price;

	@Column(name = "date_added", columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date dateAdded;

}
