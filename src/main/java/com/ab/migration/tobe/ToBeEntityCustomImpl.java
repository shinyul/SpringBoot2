package com.ab.migration.tobe;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToBeEntityCustomImpl implements ToBeEntityCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<ToBeDomain> findByProductNameKrFullText(String[] searchKeyword, Pageable pageable) {
		String findQueryStr= "SELECT tbd ";
		String queryStr = "FROM ToBeDomain tbd WHERE 1=1 ";
		String countQueryStr = "SELECT count(tbd.tobeId) ";
		
		for (int i = 0; i < searchKeyword.length; i++) {
			if(!searchKeyword[i].isEmpty()) {
				queryStr += ( queryStr.indexOf("AND") == -1 ? " AND " : " OR " ); 
				// 파람 부분을 바로 값을 넣을 수 있지만 sql 인젝션등 문제가 발생할수 있기 때문에 query.setParameter 을 이용하여 넣어주는게 좋음. 
				queryStr += "match(tbd.productNameKr, :param"+i+") > 0 ";
			}
		}
		
		List<Sort.Order> sortOrders = pageable.getSort().stream().collect(Collectors.toList());
		if (sortOrders != null && sortOrders.size() > 0) {
			for (int i = 0; i < sortOrders.size(); i++) {
				queryStr += ( queryStr.indexOf("order by") == -1 ? " order by " : " , " ); 
				queryStr += " :fParam"+i; // sortOrders.get(i).getProperty() ;// " :fParam"+i;
				queryStr += ( i+1 == sortOrders.size() ) ? " "+sortOrders.get(i).getDirection().name() : "";
				
			}
	    }
		
		Query query = entityManager.createQuery(findQueryStr + queryStr, ToBeDomain.class);
		Query countQuery = entityManager.createQuery(countQueryStr + queryStr );
		
		for (int i = 0; i < searchKeyword.length; i++) {
			if(!searchKeyword[i].isEmpty()) {
				query.setParameter("param"+i, searchKeyword[i]);
				countQuery.setParameter("param"+i, searchKeyword[i]);
			}
		}
		
		
		if (sortOrders != null && sortOrders.size() > 0) {
			for (int i = 0; i < sortOrders.size(); i++) {
				query.setParameter("fParam"+i, "tbd."+sortOrders.get(i).getProperty());
				countQuery.setParameter("fParam"+i, "tbd."+sortOrders.get(i).getProperty());
			}
	    }
		
		
		int pageNum = pageable.getPageNumber() <= 0 ? 0 : (pageable.getPageNumber()-1);
		
		query.setFirstResult(pageNum * pageable.getPageSize()); 
		query.setMaxResults(pageable.getPageSize());
		query.setHint("org.hibernate.readOnly", true);
		
		return new PageImpl<>(query.getResultList(),pageable , (long)countQuery.getSingleResult() );
	}

}
