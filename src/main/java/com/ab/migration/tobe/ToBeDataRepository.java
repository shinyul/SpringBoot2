package com.ab.migration.tobe;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToBeDataRepository extends JpaRepository<ToBeDomain, Long>, ToBeEntityCustom {

	Optional<ToBeDomain> findByTobeIdAndAsIsDomain_AsisId(long tobeId, long asisId);

}
