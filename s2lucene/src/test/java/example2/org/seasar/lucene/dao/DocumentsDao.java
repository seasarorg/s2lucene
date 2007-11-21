package example2.org.seasar.lucene.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;

import example2.org.seasar.lucene.dto.DocumentsDto;

@S2Dao(bean = DocumentsDto.class)
public interface DocumentsDao {

	public List<DocumentsDto> getAllDocumens();

	@Query("id = /*id*/1")
	@Arguments("id")
	public DocumentsDto getDocumentById(long id);

	@Query("id in /*ids*/(1)")
	@Arguments("ids")
	public List<DocumentsDto> getDocumentByIds(List ids);

	public void insert(DocumentsDto dto);

	public void update(DocumentsDto dto);

	public void delete(DocumentsDto dto);

}
