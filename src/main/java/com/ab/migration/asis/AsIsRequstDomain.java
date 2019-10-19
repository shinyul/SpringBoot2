package com.ab.migration.asis;

import org.springframework.data.domain.Pageable;

import com.ab.migration.common.PagingDomain;

import lombok.Getter;
import lombok.Setter;
//import lombok.Setter;

@Getter
@Setter
public class AsIsRequstDomain extends PagingDomain {

	
	private String productNameKr;
	
	public Pageable ofPageable() {
		
		return this.of( ( this.getSortFild() != null &&  !this.getSortFild().trim().isEmpty() ) ? this.getSortFild() : "asisId"  );
	}

}
