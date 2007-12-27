package org.seasar.lucene.common;

public enum CommandType {

	SEARCH {
		CommandType getTypeName() {
			return this;
		}
	},
	CREATE {
		CommandType getTypeName() {
			return this;
		}
	},
	UPDATE {
		CommandType getTypeName() {
			return this;
		}
	},
	DELETE {
		CommandType getTypeName() {
			return this;
		}
	}

}
