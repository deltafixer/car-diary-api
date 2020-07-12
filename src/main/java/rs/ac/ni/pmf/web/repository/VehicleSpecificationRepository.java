package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.VehicleSpecificationEntity;

@Repository
public interface VehicleSpecificationRepository extends CrudRepository<VehicleSpecificationEntity, Integer>,
		JpaSpecificationExecutor<VehicleSpecificationEntity> {

	@Override
	List<VehicleSpecificationEntity> findAll();

	VehicleSpecificationEntity findByVehicleVin(final String vehicleVin);
}
