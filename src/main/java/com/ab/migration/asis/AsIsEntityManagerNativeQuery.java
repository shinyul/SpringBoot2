package com.ab.migration.asis;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository("asIsEntityManagerNativeQuery")
public class AsIsEntityManagerNativeQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	//natevie query 를 이용하여 fulltext 조회 처리
	@SuppressWarnings("unchecked")
	public List<AsIsDomain> findByProductNameKrFullText(String[] searchKeyword) {
		
		String queryStr = "SELECT aid.* FROM as_is_data aid WHERE 1=1 ";
		for (int i = 0; i < searchKeyword.length; i++) {
			if(!searchKeyword[i].isEmpty()) {
				queryStr += ( queryStr.indexOf("AND") == -1 ? " AND " : " OR " ); 
				queryStr += "MATCH (aid.product_name_kr) AGAINST ('+" + searchKeyword[i] + "' IN BOOLEAN MODE)";
				
			}
		}
		Query query = entityManager.createNativeQuery(queryStr, AsIsDomain.class);
		
		return query.getResultList();
		
	}

	
	
}
