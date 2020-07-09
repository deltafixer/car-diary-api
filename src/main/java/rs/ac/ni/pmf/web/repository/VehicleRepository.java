package rs.ac.ni.pmf.web.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.VehicleEntity;

@Repository
public interface VehicleRepository
		extends CrudRepository<VehicleEntity, String>, JpaSpecificationExecutor<VehicleEntity> {

}
