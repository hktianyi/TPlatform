package org.tplatform.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Tianyi on 2015/3/20.
 */
@Data
public class Pagination implements Serializable {
  public Integer start;//起始标记
  public Integer end;//结束标记
  public Integer length;//单页记录数
  public Long recordsTotal;//总记录数
  public Long recordsFiltered;//过滤后记录数
  public Integer pageIndex;//当前页数
  public Integer pageTotal;//总页数

  public Pagination () {}
  public Pagination (Integer start, Integer length) {
    this.start = start;
    this.length = length;
  }

}
