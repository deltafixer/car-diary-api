package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.VehicleServiceEntity;

@Repository
public interface VehicleServiceRepository extends PagingAndSortingRepository<VehicleServiceEntity, Integer>,
		JpaSpecificationExecutor<VehicleServiceEntity> {

	@Override
	List<VehicleServiceEntity> findAll();

	List<VehicleServiceEntity> findByVehicleVin(final String vehicleVin);
}
