package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.VehicleAccidentEntity;

@Repository
public interface VehicleAccidentRepository extends PagingAndSortingRepository<VehicleAccidentEntity, Integer>,
		JpaSpecificationExecutor<VehicleAccidentEntity> {

	@Override
	List<VehicleAccidentEntity> findAll();

	List<VehicleAccidentEntity> findByVehicleVin(String vehicleVin);

}
