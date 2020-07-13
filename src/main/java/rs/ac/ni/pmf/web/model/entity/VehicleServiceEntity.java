package rs.ac.ni.pmf.web.model.entity;

import java.sql.Date;

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
@Builder()
@Entity
@Table(name = "vehicle_service")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleServiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicle_vin", nullable = false)
	private VehicleEntity vehicle;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_user_id", nullable = false)
	private ServiceUserEntity servicedBy;

	@Column(name = "date_taken", columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date dateTaken;

	@Column(nullable = false)
	private Float price;

	@Column(name = "service_details", length = 255, nullable = false)
	private String serviceDetails;

}
