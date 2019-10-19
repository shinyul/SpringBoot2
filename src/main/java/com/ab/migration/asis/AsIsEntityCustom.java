package com.ab.migration.asis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AsIsEntityCustom {

	public Page<AsIsDomain> findByProductNameKrFullText(String[] searchKeyword, Pageable pageable);
}
