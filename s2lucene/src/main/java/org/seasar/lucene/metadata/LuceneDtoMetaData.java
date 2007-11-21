package org.seasar.lucene.metadata;

public class LuceneDtoMetaData {

	private String fieldName;

	private boolean stored;

	private boolean toknize;

	private Class propertyType;

	private boolean isID;

	private String analyzerType;

	private String typeName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isStored() {
		return stored;
	}

	public void setStored(boolean stored) {
		this.stored = stored;
	}

	public boolean isToknize() {
		return toknize;
	}

	public void setToknize(boolean toknize) {
		this.toknize = toknize;
	}

	public Class getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Class propertyType) {
		this.propertyType = propertyType;
	}

	public boolean isID() {
		return isID;
	}

	public void setID(boolean isID) {
		this.isID = isID;
	}

	public String getAnalyzerType() {
		return analyzerType;
	}

	public void setAnalyzerType(String analyzerType) {
		this.analyzerType = analyzerType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
