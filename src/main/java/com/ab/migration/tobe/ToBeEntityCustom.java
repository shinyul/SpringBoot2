package com.ab.migration.tobe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ToBeEntityCustom {

	public Page<ToBeDomain> findByProductNameKrFullText(String[] searchKeyword, Pageable pageable);
}
