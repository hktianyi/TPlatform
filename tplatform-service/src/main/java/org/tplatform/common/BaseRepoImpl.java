package org.tplatform.common;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tplatform.util.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyi on 2016/11/18.
 */
public abstract class BaseRepoImpl<E> {

  @PersistenceContext
  protected EntityManager em;

  @Transactional(readOnly = true)
  public List findWithSql(String sql) {
    return em.createNativeQuery(sql).getResultList();
  }

  @Transactional(readOnly = true)
  public List findWithSql(String sql, Object... param) {
    Logger.i("findWithSql: " + sql);
    Query query = em.createNativeQuery(sql);
    if (param != null && param.length > 0)
      for (int i = 0, length = param.length; i < length; i++)
        query.setParameter(i, param[i]);
    return query.getResultList();
  }

  @Transactional(readOnly = true)
  public List findWithSql(String sql, Class result, Object... param) {
    Logger.i("findWithSql: " + sql);
    Query query = em.createNativeQuery(sql, result);
    if (param != null && param.length > 0)
      for (int i = 0, length = param.length; i < length; i++)
        query.setParameter(i, param[i]);
    Map<String, Object> aaa = query.getHints();
    return query.getResultList();
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public int executeHql(String hql, Object... param) {
    Logger.i("executeHql: " + hql);
    Query query = em.createQuery(hql);
    if (param != null && param.length > 0)
      for (int i = 0, length = param.length; i < length; i++)
        query.setParameter(i, param[i]);
    return query.executeUpdate();
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public int executeSql(String sql, Object... param) {
    Logger.i("executeSql: " + sql);
    Query query = em.createNativeQuery(sql);
    if (param != null && param.length > 0)
      for (int i = 0, length = param.length; i < length; i++)
        query.setParameter(i, param[i]);
    return query.executeUpdate();
  }

  /**
   * 查询列表
   *
   * @param e 实体
   * @param start 起始行
   * @param length 长度
   * @param selection 条件
   * @return Page
   */
  public Page<E> findForTable(E e, Integer start, Integer length, String... selection) {
    return null;
  }
}
