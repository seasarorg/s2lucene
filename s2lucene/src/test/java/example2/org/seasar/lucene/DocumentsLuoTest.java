package example2.org.seasar.lucene;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.annotation.TxBehavior;
import org.seasar.framework.unit.annotation.TxBehaviorType;

import example2.org.seasar.lucene.dao.DocumentsDao;
import example2.org.seasar.lucene.dto.DocumentsDto;
import example2.org.seasar.lucene.luo.DocumentsLuo;

@RunWith(Seasar2.class)
public class DocumentsLuoTest {

	@Binding(bindingType = BindingType.MUST)
	DocumentsLuo documentsLuo;

	@Binding(bindingType = BindingType.MUST)
	DocumentsDao documentsDao;

	String indexPath = System.getProperty("user.dir") + "\\" + "index";

	private DocumentsDto createDto(long id, String name) {
		DocumentsDto dto = null;
		dto = new DocumentsDto();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}

	/**
	 * テスト開始前に作成済みIndexを削除
	 */
	@Before
	public void inithializeIndex() {
		System.out.println(indexPath);
		try {
			FileUtils.cleanDirectory(new File(indexPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * レコード作成&検索テスト
	 */
	@Test
	public void createDaoDataTestTx() {
		// レコードの作成
		documentsDao.insert(createDto(1, "Seasar2入門"));
		List<DocumentsDto> result = documentsDao.getAllDocumens();
		assertEquals(1, result.size());
	}

	/**
	 * レコード作成&検索テスト&Index作成テスト
	 */
	@Test
	@TxBehavior(TxBehaviorType.ROLLBACK)
	public void createDaoDataAndIndexTest() {
		DocumentsDto dto = createDto(1, "Seasar2入門");
		// レコードの作成
		documentsDao.insert(dto);
		List<DocumentsDto> result = documentsDao.getAllDocumens();
		assertEquals(1, result.size());
		// Indexの作成
		documentsLuo.createIndex(dto);
		String searchWord = "入門";
		List<DocumentsDto> resultLuo = documentsLuo.searchByName(searchWord);
		assertEquals(1, resultLuo.size());
		//
		DocumentsDto resultDao = documentsDao.getDocumentById(resultLuo.get(0).getId());
		assertEquals("Seasar2入門", resultDao.getName());
	}

}
