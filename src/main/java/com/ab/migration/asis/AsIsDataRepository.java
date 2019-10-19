package com.ab.migration.asis;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsIsDataRepository extends JpaRepository<AsIsDomain, Long>, AsIsEntityCustom {
	
//	@Query("SELECT aid FROM as_is_data aid WHERE 1=1 AND MATCH (aid.product_name_kr) AGAINST (:productNameKr IN BOOLEAN MODE);")
//	public List<AsIsDomain> findByProductNameKrLike(String productNameKr);
//	@Query("select u from User u where u.lastname like %:#{[0]}% and u.lastname like %:lastname%")
	
//	@Query(value ="select a.* " + 
//			"from (" + 
//			"      select asis_id " + 
//			"      from abdata.as_is_data " + 
//			"      where asis_id between 1 and 47800 " + 
//			"      order by asis_id " + 
//			" " + 
//			") b join abdata.as_is_data a on b.asis_id = a.asis_id;", nativeQuery=true )
//	public List<AsIsDomain> findByAsIsDataAllCustom();
	
	Optional<AsIsDomain> findByAsisIdAndTobeSaveYnAndToBeDomain_TobeId(long asisId, boolean tobeSaveYn,long tobeId);
	Optional<AsIsDomain> findByAsisIdAndTobeSaveYn(long asisId , boolean tobeSaveYn);
}
