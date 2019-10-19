package com.ab.migration.asis;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@ControllerAdvice
@RequestMapping("/asis")
public class AsIsController {
	
	AsIsService asIsService;

	@GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<Page<AsIsDomain>> asIsDataFind( AsIsRequstDomain ard ) {
		Page<AsIsDomain> pageResArr = null;
		if(  ard.getProductNameKr() != null && !(ard.getProductNameKr().trim()).isEmpty() ) {
			pageResArr = asIsService.findByProductNameKrEnJPQL(ard.getProductNameKr(), ard.ofPageable());
		}else {
			pageResArr = asIsService.findAsIsData(ard.ofPageable());
		}
		
		return new ResponseEntity<>( pageResArr, HttpStatus.OK);
	}
	
	@PostMapping(path = "/convertTobe", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<AsIsDomain> asIsDataToSaveToBe( @RequestBody AsIsDomain asIsDomain ) {
//		asIsDomain.setTobeSaveYn(true);
		
		return new ResponseEntity<>( asIsService.asisDataConvertTobeSave(asIsDomain), HttpStatus.OK);
	}
	
	@PutMapping(path = "/convertTobe/{asisId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<AsIsDomain> asIsDataToModifyToBe( @RequestBody AsIsDomain asIsDomain,  @PathVariable("asisId")  long asisId ) {
//		AsIsDomain findAdResut = asIsService.findById(asisId);
//		asIsDomain.builder().asisId(findAdResut.getAsisId()).tobeSaveYn(true);
//		asIsDomain.getToBeDomain().builder().tobeId(findAdResut.getToBeDomain().getTobeId());
		
		return new ResponseEntity<>( asIsService.asisDataConvertTobeModify(asIsDomain, asisId), HttpStatus.OK);
	}
	
}
