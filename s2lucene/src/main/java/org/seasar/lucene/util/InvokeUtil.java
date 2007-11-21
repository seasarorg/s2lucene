package org.seasar.lucene.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.seasar.lucene.exception.IllegalAccessRuntimeException;
import org.seasar.lucene.exception.InvocationTargetRuntimeException;


public class InvokeUtil {

	public static Object invoke(Method method, Object invokeTarget) {
		try {
			return method.invoke(invokeTarget);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new InvocationTargetRuntimeException(e);
		}
	}

	public static Object invoke(Method method, Object param, Object invokeTarget) {
		try {
			return method.invoke(invokeTarget, param);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new InvocationTargetRuntimeException(e);
		}
	}

}
