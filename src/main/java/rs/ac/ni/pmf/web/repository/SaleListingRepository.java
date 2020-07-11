package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javassist.NotFoundException;
import rs.ac.ni.pmf.web.model.entity.SaleListingEntity;

@Repository
public interface SaleListingRepository
		extends PagingAndSortingRepository<SaleListingEntity, Integer>, JpaSpecificationExecutor<SaleListingEntity> {

	@Override
	List<SaleListingEntity> findAll();

	SaleListingEntity findByVehicleVin(String vehicleVin) throws NotFoundException;

}
