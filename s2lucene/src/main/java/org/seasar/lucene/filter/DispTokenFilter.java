package org.seasar.lucene.filter;

import java.io.IOException;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class DispTokenFilter extends TokenFilter {

	public DispTokenFilter(TokenStream in) {
		super(in);
		System.out.println("|テキスト\t\t|位置増分\t|開始\t|終了\t|種別");
		System.out.println("+---------------+---------------+-------+-------+-------");
		input = in;
	}

	@Override
	public Token next() throws IOException {
		Token t = input.next();
		if (t == null) {
			return null;
		}
		String text = t.termText();
		String posinc = Integer.toString(t.getPositionIncrement());
		String start = Integer.toString(t.startOffset());
		String end = Integer.toString(t.endOffset());
		String type = t.type();
		System.out.println(" " + text + "\t\t" + posinc + "\t" + start + "\t " + end + "\t " + type);
		return t;
	}

}
