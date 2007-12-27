package org.seasar.lucene;

import java.util.List;

import org.seasar.extension.unit.S2TestCase;

public class S2LuceneFunctionalTest extends S2TestCase {

	String path = "org/seasar/lucene/s2LuceneFunctionalTest.dicon";
	private DocumentLuo luo;

	@Override
	protected void setUp() throws Exception {
		include(path);
	}

	public void test実現機能テスト() {
		DocumentDto dto = new DocumentDto();
		String createText = "Lucene入門";
		dto.setText(createText);

		luo.create(dto);

		String searchWord = "入門";
		List<DocumentDto> luoResult = luo.search(searchWord);
		assertEquals(1, luoResult.size());
		assertEquals(searchWord, luoResult.get(0).getText());

		String updateText = "Lucene応用";
		dto.setText(updateText);
		luo.update(dto);

		String searchWord2 = "応用";
		List<DocumentDto> luoResult2 = luo.search(searchWord2);
		assertEquals(1, luoResult2.size());
		assertEquals(searchWord2, luoResult2.get(0).getText());

		luo.delete(dto);

		String searchWord3 = "応用";
		List<DocumentDto> luoResult3 = luo.search(searchWord3);
		assertEquals(0, luoResult3.size());
	}

}
