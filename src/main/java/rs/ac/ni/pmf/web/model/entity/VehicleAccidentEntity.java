package rs.ac.ni.pmf.web.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "vehicle_accident")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAccidentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_vin")
	private VehicleEntity vehicle;

	@Column(name = "date_of_accident")
	private Date dateOfAccident;

	@Column(name = "damage_price_evaluation")
	private Float damagePriceEvaluation;
	
}
