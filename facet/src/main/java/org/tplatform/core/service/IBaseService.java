package org.tplatform.core.service;

import com.github.pagehelper.PageInfo;
import org.tplatform.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tianyi on 2016/3/21.
 */
public abstract interface IBaseService<E extends BaseEntity> {

  //新增
  abstract int save(E e);

  //更新
  abstract int update(E e);

  //更新
  abstract int saveOrUpdate(E e);

  // 删除
  abstract int delete(E e);

  abstract int delete(Serializable id);

  // 查询类
  abstract E find(Serializable id);

  abstract List<E> find(E e);

  abstract E findOne(E e);

  abstract PageInfo<E> find(E e, PageInfo<E> pageInfo);

  abstract List<E> findAll();

  // 查询记录数
  abstract int count(E e);
}
