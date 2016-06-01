package org.tplatform.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.tplatform.core.entity.BaseEntity;
import org.tplatform.core.service.IBaseService;
import org.tplatform.framework.util.DateUtil;
import org.tplatform.framework.util.SpringContextUtil;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 通用服务类，依赖通用Mapper 提供通用增删改查功能
 * Created by Tianyi on 2015/12/22.
 */
public abstract class BaseService<E extends BaseEntity> implements IBaseService<E> {

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  protected Mapper<E> mapper;

  // 新增
  public int save(E e) {
    e.setTimestamp(DateUtil.getTimeInMillis());
    e.setOperator(SpringContextUtil.getOperator());
    return mapper.insert(e);
  }

  // 更新
  public int update(E e) {
    return mapper.updateByPrimaryKeySelective(e);
  }

  // 保存或更新
  public int saveOrUpdate(E e) {
    int result;
    if (e.getId() != null && e.getId() > 0) result = update(e);
    else result = save(e);
    return result;
  }

  // 删除
  public int delete(E e) {
    return mapper.delete(e);
  }

  public int delete(Serializable id) {
    return mapper.deleteByPrimaryKey(id);
  }

  // 查询类
  public E find(Serializable id) {
    return mapper.selectByPrimaryKey(id);
  }

  public List<E> find(E e) {
    return mapper.select(e);
  }

  public E findOne(E e) {
    return mapper.selectOne(e);
  }

  public PageInfo<E> find(E e, PageInfo<E> pageInfo) {
    if (pageInfo.getPageNum() == 0) {
      pageInfo.setPageNum(pageInfo.getStartRow() / pageInfo.getPageSize() + 1);
    }
    PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
    return new PageInfo<>(mapper.select(e));
  }

  public List<E> findAll() {
    return mapper.selectAll();
  }

  // 查询记录数
  public int count(E e) {
    return mapper.selectCount(e);
  }

}
