package org.seasar.lucene.util;

import java.io.IOException;

import org.apache.lucene.store.Directory;
import org.seasar.lucene.exception.IORuntimeException;


public class DirectoryUtil {

	public static void close(Directory directory) {
		try {
			directory.close();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

}
