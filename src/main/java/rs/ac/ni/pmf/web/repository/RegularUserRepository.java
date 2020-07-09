package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.RegularUserEntity;

@Repository
public interface RegularUserRepository
		extends CrudRepository<RegularUserEntity, String>, JpaSpecificationExecutor<RegularUserEntity> {

	@Override
	List<RegularUserEntity> findAll();

}
