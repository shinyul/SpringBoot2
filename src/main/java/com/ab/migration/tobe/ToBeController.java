package com.ab.migration.tobe;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/tobe")
public class ToBeController {
	
	ToBeService toBeService;

	//상품명
	@GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<Page<ToBeDomain>> toBeDataFind(ToBeRequstDomain tbrd) {
		Page<ToBeDomain> pageResArr = null;
		
		if(  tbrd.getProductNameKr() != null && !(tbrd.getProductNameKr().trim()).isEmpty() ) {
			pageResArr = toBeService.findByProductNameKrEnJPQL(tbrd.getProductNameKr(), tbrd.ofPageable());
		}else {
			pageResArr = toBeService.findToBeData(tbrd.ofPageable());
		}
		
		return new ResponseEntity<>(pageResArr,HttpStatus.OK);
	}
	
//	@PostMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
//	public ResponseEntity<ToBeDomain> saveToBeData(@RequestBody ToBeDomain tbd) {
//		tbd.getAsIsDomain().setTobeSaveYn(true);
//		
//		return new ResponseEntity<>(toBeService.saveToBeData(tbd),HttpStatus.OK);
//	}

	
	@PutMapping(value = "/{tobeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE ) 
	public ResponseEntity<Object> updateStudent( @RequestBody ToBeDomain tbd, @PathVariable("tobeId") long tobeId) {
//		tbd.setAsisIdToAsIsDomain();
		return new ResponseEntity<>(toBeService.saveToBeData(tbd,tobeId),HttpStatus.OK);
	}
}
