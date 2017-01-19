package org.tplatform.doc;

import org.springframework.data.jpa.repository.Query;
import org.tplatform.common.BaseRepo;

import java.util.List;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface DocService extends BaseRepo<Doc> {

  @Query("select new Doc(id, title, summary, author, keyword, imgUrl) from Doc order by id desc")
  List<Doc> queryTop();
}
