package org.tplatform.core.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 系统配置实体
 * Created by Tianyi on 2015/1/6.
 */
@Table(name = "sys_conf")
public class SysConf extends BaseEntity {

  @Column(length = 20)
  private Long pId;
  @Column(length = 10)
  private String type;
  @Column(length = 32, unique = true)
  private String confKey;
  @Column(length = 20)
  private String name;
  @Column(length = 100)
  private String val;
  @Column(length = 100)
  private String remark;
  @Column(scale = 1)
  private Integer sort;
  @Column(scale = 1)
  private Integer level;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getpId() {
    return pId;
  }

  public void setpId(Long pId) {
    this.pId = pId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getConfKey() {
    return confKey;
  }

  public void setConfKey(String confKey) {
    this.confKey = confKey;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVal() {
    return val;
  }

  public void setVal(String val) {
    this.val = val;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }
}
