package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.PersonUserEntity;

@Repository
public interface PersonUserRepository
		extends PagingAndSortingRepository<PersonUserEntity, String>, JpaSpecificationExecutor<PersonUserEntity> {

	@Override
	List<PersonUserEntity> findAll();

}
