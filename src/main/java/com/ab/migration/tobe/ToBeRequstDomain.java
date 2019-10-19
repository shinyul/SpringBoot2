package com.ab.migration.tobe;

import org.springframework.data.domain.Pageable;

import com.ab.migration.common.PagingDomain;

import lombok.Getter;
import lombok.Setter;
//import lombok.Setter;

@Getter
@Setter
public class ToBeRequstDomain extends PagingDomain {

	
	private String productNameKr;
	
	public Pageable ofPageable() {
		
		return this.of( ( this.getSortFild() != null &&  !this.getSortFild().trim().isEmpty() ) ? this.getSortFild() : "tobeId"  );
	}

}
