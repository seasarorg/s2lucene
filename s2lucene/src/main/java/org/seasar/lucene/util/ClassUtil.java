package org.seasar.lucene.util;

import org.seasar.lucene.exception.ClassNotFoundRuntimeException;
import org.seasar.lucene.exception.IllegalAccessRuntimeException;
import org.seasar.lucene.exception.InstantiationRuntimeException;

public class ClassUtil {

	public static Class forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundRuntimeException(e);
		}
	}

	public static Object newInstance(Class clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new InstantiationRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		}
	}

	public static String convertClassName(String className) {
		return className.substring(0, 1).toUpperCase() + className.subSequence(1, className.length());
	}

	public static String parseClassName(Class clazz) {
		String aopClassName = clazz.getName();
		String className = aopClassName.substring(0, aopClassName.indexOf("$$"));
		return className;
	}

}
