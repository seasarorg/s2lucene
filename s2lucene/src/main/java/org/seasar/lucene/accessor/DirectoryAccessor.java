package org.seasar.lucene.accessor;

import org.apache.lucene.store.Directory;

public interface DirectoryAccessor {

	public void open();

	public Directory getDirectory();

	public void close();
}
