package org.seasar.lucene.accessor.impl;

import java.io.IOException;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.seasar.lucene.accessor.DirectoryAccessor;
import org.seasar.lucene.exception.IORuntimeException;
import org.seasar.lucene.util.DirectoryUtil;

public class DirectoryAccessorImpl implements DirectoryAccessor {

	private Directory directory;

	private String path;

	public void close() {
		DirectoryUtil.close(directory);
	}

	public void open() {
		try {
			directory = FSDirectory.getDirectory(path);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public Directory getDirectory() {
		return directory;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
