package org.seasar.lucene.metadata;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.seasar.lucene.annotation.LuceneField;
import org.seasar.lucene.annotation.LuceneDto;
import org.seasar.lucene.annotation.LuceneId;
import org.seasar.lucene.exception.AnnotationNotFoundRuntimeException;


public class LuceneDtoMetaDataFactory {

	public static List<LuceneDtoMetaData> createMetaData(Class dtoClass) {
		List<LuceneDtoMetaData> metaDatas = new ArrayList<LuceneDtoMetaData>();

		LuceneDto luceneDto = (LuceneDto) dtoClass.getAnnotation(LuceneDto.class);
		if (luceneDto == null) {
			throw new AnnotationNotFoundRuntimeException("LuceneDto");
		}
		String typeName = luceneDto.typeName();

		Field[] fields = dtoClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			LuceneField lucene = field.getAnnotation(LuceneField.class);
			if (lucene == null) {
				continue;
			}
			LuceneDtoMetaData metaData = new LuceneDtoMetaData();
			metaData.setFieldName(lucene.fieldName());
			metaData.setStored(lucene.store());
			metaData.setToknize(lucene.toknize());
			metaData.setPropertyType(field.getType());
			LuceneId luceneId = (LuceneId) field.getAnnotation(LuceneId.class);
			if (luceneId != null) {
				metaData.setID(true);
			} else {
				metaData.setID(false);
			}
			metaData.setAnalyzerType(lucene.analyzerType());
			metaData.setTypeName(typeName);
			metaDatas.add(metaData);
		}
		return metaDatas;
	}

}
