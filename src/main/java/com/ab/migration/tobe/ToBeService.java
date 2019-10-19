package com.ab.migration.tobe;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ab.migration.exception.CustomException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("toBeService")
public class ToBeService {

	ToBeDataRepository toBeDataRepository;
	
	public Page<ToBeDomain> findToBeData(Pageable pageable) {
		
		return toBeDataRepository.findAll(pageable);
	}

	public ToBeDomain saveToBeData(ToBeDomain tbd, long tobeId) {
		ToBeDomain tbdCheck = findByIdAndAsIsId(tobeId, tbd.getAsIsDomain().getAsisId());
//		ToBeDomain tbdCheck = findById(tobeId);
		
		if( tbdCheck.getAsIsDomain() == null && tbdCheck.getAsIsDomain().getAsisId() <= 1 && tbd.getAsIsDomain().getAsisId() != tbdCheck.getAsIsDomain().getAsisId() ) {
			throw new CustomException("ASIS data does not exist. Please check again.", HttpStatus.PRECONDITION_FAILED);
		}
		
		tbd.setTobeId(tbdCheck.getTobeId());
		return toBeDataRepository.save(tbd);
	}
	
	public ToBeDomain findById(long id) {
		Optional<ToBeDomain> tobeDomainOptional = toBeDataRepository.findById(id);
		
		if (!tobeDomainOptional.isPresent()) {
			throw new CustomException("Tobe data does not exist. Please check again.", HttpStatus.PRECONDITION_FAILED);
		}
			
		return tobeDomainOptional.get();
	
	}
	
	public ToBeDomain findByIdAndAsIsId(long tobeId, long asisId) {
		Optional<ToBeDomain> tobeDomainOptional = toBeDataRepository.findByTobeIdAndAsIsDomain_AsisId(tobeId,asisId);
		
		if (!tobeDomainOptional.isPresent()) {
			throw new CustomException("Tobe data does not exist. Please check again.", HttpStatus.PRECONDITION_FAILED);
		}
			
		return tobeDomainOptional.get();
	
	}

	public Page<ToBeDomain> findByProductNameKrEnJPQL(String productNameKr, Pageable pageable) {
		// TODO Auto-generated method stub
		return toBeDataRepository.findByProductNameKrFullText(productNameKr.trim().split(" "), pageable);
	}
}
