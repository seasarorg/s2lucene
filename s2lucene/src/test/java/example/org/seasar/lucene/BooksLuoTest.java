package example.org.seasar.lucene;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.unit.Seasar2;

import example.org.seasar.lucene.dto.BooksDto;
import example.org.seasar.lucene.luo.BooksLuo;

@RunWith(Seasar2.class)
public class BooksLuoTest {

	@Binding(bindingType = BindingType.MUST)
	BooksLuo booksLuo;

	String indexPath = System.getProperty("user.dir") + "\\" + "index";

	String textPath = System.getProperty("user.dir") + "\\" + "text";

	String encoding = "UTF-8";

	private BooksDto createDto(long id, String name) {
		BooksDto dto = null;
		dto = new BooksDto();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}

	/**
	 * テスト開始前に作成済みIndexを削除
	 */
	@Before
	public void inithializeIndex() {
		File indexDir = new File(indexPath);
		try {
			FileUtils.cleanDirectory(indexDir);
		} catch (IllegalArgumentException e) {
			System.out.println("Index用ディレクトリを作成します。");
			indexDir.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Index作成&検索テスト
	 */
	@Test
	public void createIndexAndSearch() {
		booksLuo.createIndex(createDto(1, "Seasar2入門"));
		String searchWord = "入門";
		List<BooksDto> result = booksLuo.searchByName(searchWord);
		assertEquals(1, result.size());
	}

	/**
	 * Index作成&検索テスト
	 */
	@Test
	public void createIndexManyAndSearch() {
		booksLuo.createIndex(createDto(1, "Seasar2入門"));
		booksLuo.createIndex(createDto(2, "S2Dao入門"));
		booksLuo.createIndex(createDto(3, "teeda入門"));
		booksLuo.createIndex(createDto(4, "s2jsf入門"));
		booksLuo.createIndex(createDto(5, "kuina入門"));
		booksLuo.createIndex(createDto(6, "S2Mai入門"));
		booksLuo.createIndex(createDto(7, "S2Sao入門"));
		booksLuo.createIndex(createDto(8, "Buri入門"));
		booksLuo.createIndex(createDto(9, "Ebi入門"));
		booksLuo.createIndex(createDto(10, "Ana5入門"));
		booksLuo.createIndex(createDto(11, "DBFlute入門"));
		String searchWord = "入門";
		List<BooksDto> result = booksLuo.searchByName(searchWord);
		assertEquals(11, result.size());
	}

	/**
	 * Index作成&検索テスト
	 */
	@Test
	public void createIndexManyAndSearch2() {
		booksLuo.createIndex(createDto(1, "Seasar2入門"));
		booksLuo.createIndex(createDto(2, "S2Dao入門"));
		booksLuo.createIndex(createDto(3, "teeda入門"));
		booksLuo.createIndex(createDto(4, "s2jsf入門"));
		booksLuo.createIndex(createDto(5, "kuina入門"));
		booksLuo.createIndex(createDto(6, "S2Mai入門"));
		booksLuo.createIndex(createDto(7, "S2Sao入門"));
		booksLuo.createIndex(createDto(8, "Buri入門"));
		booksLuo.createIndex(createDto(9, "Ebi入門"));
		booksLuo.createIndex(createDto(10, "Ana5入門"));
		booksLuo.createIndex(createDto(11, "DBFlute入門"));
		List<BooksDto> result = booksLuo.searchById("7");
		assertNotNull(result.get(0));
		assertEquals("S2Sao入門", result.get(0).getName());
		System.out.println(result.toString());
	}

	/**
	 * Index作成&検索テスト&更新テスト
	 */
	@Test
	public void createIndexManyAndUpdate() {
		booksLuo.createIndex(createDto(1, "Seasar2入門"));
		booksLuo.createIndex(createDto(2, "S2Dao入門"));
		booksLuo.createIndex(createDto(3, "teeda入門"));
		booksLuo.createIndex(createDto(4, "s2jsf入門"));
		booksLuo.createIndex(createDto(5, "kuina入門"));
		booksLuo.createIndex(createDto(6, "S2Mai入門"));
		booksLuo.createIndex(createDto(7, "S2Sao入門"));
		booksLuo.createIndex(createDto(8, "Buri入門"));
		booksLuo.createIndex(createDto(9, "Ebi入門"));
		booksLuo.createIndex(createDto(10, "Ana5入門"));
		booksLuo.createIndex(createDto(11, "DBFlute入門"));
		List<BooksDto> result = booksLuo.searchById("7");
		assertNotNull(result.get(0));
		assertEquals("S2Sao入門", result.get(0).getName());
		System.out.println(result.toString());
		//
		booksLuo.updateIndex(createDto(7, "S2Flex2入門"));
		List<BooksDto> resultAfter = booksLuo.searchById("7");
		assertNotNull(resultAfter.get(0));
		assertEquals("S2Flex2入門", resultAfter.get(0).getName());
		System.out.println(resultAfter.toString());
	}

	/**
	 * Index作成&検索テスト&削除テスト
	 */
	@Test
	public void createIndexManyAndDelete() {
		booksLuo.createIndex(createDto(1, "Seasar2入門"));
		booksLuo.createIndex(createDto(2, "S2Dao入門"));
		booksLuo.createIndex(createDto(3, "teeda入門"));
		booksLuo.createIndex(createDto(4, "s2jsf入門"));
		booksLuo.createIndex(createDto(5, "kuina入門"));
		booksLuo.createIndex(createDto(6, "S2Mai入門"));
		booksLuo.createIndex(createDto(7, "S2Sao入門"));
		booksLuo.createIndex(createDto(8, "Buri入門"));
		booksLuo.createIndex(createDto(9, "Ebi入門"));
		booksLuo.createIndex(createDto(10, "Ana5入門"));
		booksLuo.createIndex(createDto(11, "DBFlute入門"));
		List<BooksDto> result = booksLuo.searchById("7");
		assertNotNull(result.get(0));
		assertEquals("S2Sao入門", result.get(0).getName());
		System.out.println(result.toString());
		//
		booksLuo.deleteIndex(createDto(7, "S2Flex2入門"));
		List<BooksDto> resultAfter = booksLuo.searchById("7");
		assertEquals(0, resultAfter.size());
	}

	@Test
	public void createIndexAndSearchJpTest() {
		long start = 0;
		long stop = 0;
		List<String> result = testRead();
		long id = 1;
		//
		start = System.currentTimeMillis();
		for (int i = 0; i < result.size(); i++) {
			String name = result.get(i);
			booksLuo.createIndex(createDto(id, name));
			id = id + 1;
		}
		stop = System.currentTimeMillis();
		System.out.println(stop - start);
		//
		start = System.currentTimeMillis();
		List<BooksDto> resultSearch = booksLuo.searchByName("ライブラリ");
		for (int i = 0; i < resultSearch.size(); i++) {
			String dtoStr = resultSearch.get(i).toString();
			System.out.println(dtoStr);
		}
		stop = System.currentTimeMillis();
		System.out.println(stop - start);
	}

	private List<String> testRead() {
		String testFile = textPath + "\\" + "test.txt";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		try {
			fis = new FileInputStream(testFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			isr = new InputStreamReader(fis, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		bReader = new BufferedReader(isr);
		List<String> result = new ArrayList<String>();
		String resultStr = null;
		try {
			while (!((resultStr = bReader.readLine()) == null)) {
				result.add(resultStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
