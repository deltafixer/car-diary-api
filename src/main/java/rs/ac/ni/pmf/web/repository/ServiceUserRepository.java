package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.ServiceUserEntity;

@Repository
public interface ServiceUserRepository
		extends CrudRepository<ServiceUserEntity, Integer>, JpaSpecificationExecutor<ServiceUserEntity> {

	@Override
	List<ServiceUserEntity> findAll();

}
