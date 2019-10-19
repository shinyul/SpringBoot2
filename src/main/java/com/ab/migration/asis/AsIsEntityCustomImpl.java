package com.ab.migration.asis;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AsIsEntityCustomImpl implements AsIsEntityCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<AsIsDomain> findByProductNameKrFullText(String[] searchKeyword, Pageable pageable) {
		String findQueryStr= "select aid ";
		String queryStr = "from AsIsDomain aid where 1=1 ";
		String countQueryStr = "select count(aid.asisId) ";
		
		for (int i = 0; i < searchKeyword.length; i++) {
			if(!searchKeyword[i].isEmpty()) {
				queryStr += ( queryStr.indexOf("and") == -1 ? " and " : " or " ); 
				// 파람 부분을 바로 값을 넣을 수 있지만 sql 인젝션등 문제가 발생할수 있기 때문에 query.setParameter 을 이용하여 넣어주는게 좋음. 
				queryStr += " match( aid.productNameKr, :param"+i+" )   > 0 ";
			}
		}
		
		queryStr += " order by aid.asisId ASC";
		
		Query query = entityManager.createQuery(findQueryStr + queryStr, AsIsDomain.class);
		Query countQuery = entityManager.createQuery(countQueryStr + queryStr);
		
		for (int i = 0; i < searchKeyword.length; i++) {
			if(!searchKeyword[i].isEmpty()) {
				query.setParameter( ("param"+i ) , searchKeyword[i]);
				countQuery.setParameter( ("param"+i) , searchKeyword[i]);
			}
		}
		
		int pageNum = pageable.getPageNumber() <= 0 ? 0 : (pageable.getPageNumber()-1);
		
		query.setFirstResult(pageNum * pageable.getPageSize()); 
		query.setMaxResults(pageable.getPageSize());
		query.setHint("org.hibernate.readOnly", true);
		
		return new PageImpl<>(query.getResultList(),pageable , (long)countQuery.getSingleResult());
	}
}
