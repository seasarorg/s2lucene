package org.seasar.lucene.util;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.lucene.document.Document;
import org.seasar.lucene.metadata.LuceneDtoMetaData;

public class ValueUtil {

	public static Object setupObject(Document document, List<LuceneDtoMetaData> metaDatas, Class dtoClass) {
		Object target = ClassUtil.newInstance(dtoClass);
		for (LuceneDtoMetaData metaData : metaDatas) {
			String retValue = document.get(metaData.getFieldName());
			ValueUtil.setValue(metaData, retValue, target);
		}
		return target;
	}

	public static Object setValue(LuceneDtoMetaData metaData, String param, Object target) {
		String methodName = "set" + ClassUtil.convertClassName(metaData.getFieldName());
		Method setter = MethodUtil.getDeclearedMethod(target.getClass(), methodName, metaData.getPropertyType());
		Object paramObj = null;
		if (metaData.getPropertyType() == String.class) {
			paramObj = param;
		}
		if (metaData.getPropertyType() == long.class) {
			paramObj = Long.valueOf(param);
		}
		InvokeUtil.invoke(setter, paramObj, target);
		return target;
	}

	public static String getValue(LuceneDtoMetaData metaData, Object target) {
		String methodName = "get" + ClassUtil.convertClassName(metaData.getFieldName());
		Method getter = MethodUtil.getDeclearedMethod(target.getClass(), methodName);
		Object value = InvokeUtil.invoke(getter, target);
		if (metaData.getPropertyType() == String.class) {
			return (String) value;
		}
		if (metaData.getPropertyType() == long.class) {
			return ((Long) value).toString();
		}
		return null;
	}

}
