package org.seasar.lucene.util;

import java.lang.reflect.Method;

import org.seasar.lucene.exception.NoSuchMethodRuntimeException;


public class MethodUtil {

	public static Method getDeclearedMethod(Class targetClass, String methodName) {
		try {
			return targetClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodRuntimeException(e);
		}
	}

	public static Method getDeclearedMethod(Class targetClass, String methodName, Class paramType) {
		try {
			return targetClass.getDeclaredMethod(methodName, paramType);
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodRuntimeException(e);
		}
	}

}
