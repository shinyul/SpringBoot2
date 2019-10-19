package com.ab.migration.common;

import org.hibernate.dialect.MariaDB102Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MariadbDialectCustom extends MariaDB102Dialect {
	
	public MariadbDialectCustom() {
		super();
	    registerFunction("match", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,"match(?1) against  (?2 in boolean mode)"));
	}
	
//	public MariadbDialectCustom() {
//	    super();
//	    registerFunction("match", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,"match(?1) against  (?2 in boolean mode) > 0"));
//	}
	
}
