package org.seasar.lucene.util;

import java.util.List;

import org.seasar.lucene.exception.IDNotFoundRuntimeException;
import org.seasar.lucene.metadata.LuceneDtoMetaData;


public class IDUtil {

	public static String getIdName(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		for (LuceneDtoMetaData metaData : metaDatas) {
			if (metaData.isID()) {
				return metaData.getFieldName();
			}
		}
		throw new IDNotFoundRuntimeException();
	}

	public static String getIdValue(List<LuceneDtoMetaData> metaDatas, Object targetData) {
		for (LuceneDtoMetaData metaData : metaDatas) {
			if (metaData.isID()) {
				return ValueUtil.getValue(metaData, targetData);
			}
		}
		throw new IDNotFoundRuntimeException();
	}

}
