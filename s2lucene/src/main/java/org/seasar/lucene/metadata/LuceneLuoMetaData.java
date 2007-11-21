package org.seasar.lucene.metadata;

public class LuceneLuoMetaData {

	private String fieldName;

	private String type;

	private Class dtoClass;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Class getDtoClass() {
		return dtoClass;
	}

	public void setDtoClass(Class dtoClass) {
		this.dtoClass = dtoClass;
	}

}
