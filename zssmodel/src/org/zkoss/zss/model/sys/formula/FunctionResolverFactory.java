/* FunctionResolverFactory.java

	Purpose:
		
	Description:
		
	History:
		Dec 26, 2013 Created by Pao Wang

Copyright (C) 2013 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zss.model.sys.formula;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.zkoss.lang.Library;
import org.zkoss.zss.model.impl.sys.formula.FunctionResolverImpl;

/**
 * A factory of formula function resolver.
 * @author Pao
 */
public class FunctionResolverFactory {
	static private Logger logger = Logger.getLogger(FunctionResolverFactory.class.getName());

	private static Class<?> functionResolverClazz;
	static {
		String clz = Library.getProperty("org.zkoss.zss.model.FunctionResolver.class");
		if(clz!=null){
			try {
				functionResolverClazz = Class.forName(clz);
			} catch(Exception e) {
				logger.log(Level.WARNING, e.getMessage(), e);
			}			
		}
	}

	public static FunctionResolver createFunctionResolver() {
		try {
			if(functionResolverClazz != null) {
				return (FunctionResolver)functionResolverClazz.newInstance();
			}
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			functionResolverClazz = null;
		}
		return new FunctionResolverImpl();
	}
}