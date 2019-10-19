package com.ab.migration.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Setter
public class PagingDomain {
	private int page;
	private int size;
	private Sort.Direction direction = Sort.Direction.ASC;
	@Getter
	private String sortFild;
	
	
	
	private int pageCheck() {
		return page <= 0 ? 1 : page;
	}
	
	private int sizeLimit() {
		if(size <= 20) {
			return 20;
		}else if (size > 10000) {
			return 10000;
		}else {
			return size;
		}
	}
	
	
	public Pageable of() {
		return PageRequest.of( ( pageCheck()-1 ), sizeLimit(), direction, sortFild);
	}
	
	public Pageable of(String sortFild) {
		return PageRequest.of(( pageCheck()-1 ), sizeLimit(), direction, sortFild);
	}
	
}
