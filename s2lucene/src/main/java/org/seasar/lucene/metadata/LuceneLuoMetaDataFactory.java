package org.seasar.lucene.metadata;

import java.lang.reflect.Method;

import org.seasar.lucene.annotation.S2Lucene;
import org.seasar.lucene.annotation.Search;
import org.seasar.lucene.exception.AnnotationNotFoundRuntimeException;
import org.seasar.lucene.util.ClassUtil;


public class LuceneLuoMetaDataFactory {

	public static LuceneLuoMetaData createMetaData(Class targetClass, Method targetMethod) {
		LuceneLuoMetaData metaData = new LuceneLuoMetaData();

		String className = ClassUtil.parseClassName(targetClass);
		Class targetBaseClass = ClassUtil.forName(className);

		S2Lucene s2Lucene = (S2Lucene) targetBaseClass.getAnnotation(S2Lucene.class);
		if (s2Lucene == null) {
			throw new AnnotationNotFoundRuntimeException("@S2Luceneなし");
		}
		Class dtoClass = s2Lucene.dto();
		metaData.setDtoClass(dtoClass);

		Search search = targetMethod.getAnnotation(Search.class);
		if (search != null) {
			String type = search.type();
			String fieldName = search.fieldName();
			metaData.setFieldName(fieldName);
			metaData.setType(type);
		}

		return metaData;
	}
}
