package rs.ac.ni.pmf.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.VehicleEntity;

// can't place 'int' to match id type of VehicleEntity
@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity, Integer> {

}
