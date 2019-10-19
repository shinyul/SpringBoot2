package com.ab.migration.asis;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.migration.exception.CustomException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("asIsService")
public class AsIsService {

	AsIsDataRepository asIsDataRepository;
	AsIsEntityManagerNativeQuery asIsEntityManagerNativeQuery;
	
	@Transactional(readOnly = true)
	public Page<AsIsDomain> findAsIsData(Pageable pageable) {
		
		return asIsDataRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<AsIsDomain> findByProductNameKrEnJPQL(String productNameKr , Pageable pageable) {
		
		return asIsDataRepository.findByProductNameKrFullText(productNameKr.trim().split(" "), pageable);
	}
	
	@Transactional(readOnly = true)
	public List<AsIsDomain> findByProductNameKrEnNateviQuery(String productNameKr) {
		
		return asIsEntityManagerNativeQuery.findByProductNameKrFullText(productNameKr.trim().split(" "));
	}
	
	@Transactional
	public AsIsDomain asisDataConvertTobeSave(AsIsDomain asIsDomain) {
		AsIsDomain asIsCheck = findByIdAndTobeSaveYn(asIsDomain.getAsisId(), false);
		
		if( asIsCheck.getToBeDomain() != null && asIsCheck.getToBeDomain().getTobeId() > 0 ) {
			throw new CustomException("TOBE data exists and cannot be saved. Please check again.",HttpStatus.CONFLICT);
		}
		
		asIsDomain.setTobeSaveYn(true);
		
		return asIsDataRepository.save(asIsDomain);
	}
	
	@Transactional
	public AsIsDomain asisDataConvertTobeModify(AsIsDomain asIsDomain, long asisId ) {
		AsIsDomain asIsCheck = findByIdAndTobeSaveYnAndTobeId(asisId, true, asIsDomain.getToBeDomain().getTobeId());
		
		if( asIsCheck.getToBeDomain() == null && asIsCheck.getToBeDomain().getTobeId() < 1 && asisId != asIsDomain.getAsisId() ) {
			throw new CustomException("TOBE data exists and cannot be saved. Please check again.",HttpStatus.CONFLICT);
		}
		
		asIsDomain.setTobeSaveYn(true);
		
		return asIsDataRepository.save(asIsDomain);
	}
	
	@Transactional(readOnly = true)
	public AsIsDomain findById(long id) {
		Optional<AsIsDomain> asIsDomainOptional = asIsDataRepository.findById(id);
		
		if (!asIsDomainOptional.isPresent()) {
			throw new CustomException("ASIS data does not exist. Please check again.", HttpStatus.PRECONDITION_FAILED);
		}
		
		return asIsDomainOptional.get();
	}
	
	@Transactional(readOnly = true)
	public AsIsDomain findByIdAndTobeSaveYn(long id, boolean saveYn) {
		Optional<AsIsDomain> asIsDomainOptional = asIsDataRepository.findByAsisIdAndTobeSaveYn(id, saveYn);
		
		if (!asIsDomainOptional.isPresent()) {
			throw new CustomException("ASIS data does not exist. Please check again.", HttpStatus.PRECONDITION_FAILED);
		}
		
		return asIsDomainOptional.get();
	}
	
	@Transactional(readOnly = true)
	public AsIsDomain findByIdAndTobeSaveYnAndTobeId(long asisId, boolean tobeSaveYn,long tobeId) {
		Optional<AsIsDomain> asIsDomainOptional = asIsDataRepository.findByAsisIdAndTobeSaveYnAndToBeDomain_TobeId(asisId, tobeSaveYn, tobeId);
		
		if (!asIsDomainOptional.isPresent()) {
			throw new CustomException("ASIS data does not exist. Please check again.", HttpStatus.PRECONDITION_FAILED);
		}
		
		return asIsDomainOptional.get();
	}

}
