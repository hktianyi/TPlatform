package org.tplatform.common;

import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tianyi on 2016/11/18.
 */
public abstract class BaseRepoImpl<E> {

  @PersistenceContext
  protected EntityManager em;

  public List<E> findWithSql(String sql) {
    return em.createNativeQuery(sql).getResultList();
  }

  public List<E> findWithSql(String sql, Object... param) {
    Query query = em.createNativeQuery(sql);
    if (param != null && param.length > 0)
      for (int i = 0, length = param.length; i < length; i++)
        query.setParameter(i, param[i]);
    return query.getResultList();
  }

  /**
   * 查询列表
   *
   * @param e
   * @param start
   * @param length
   * @param selection
   * @return
   */
  public Page<E> findForTable(E e, Integer start, Integer length, String... selection) {
    return null;
  }
}
