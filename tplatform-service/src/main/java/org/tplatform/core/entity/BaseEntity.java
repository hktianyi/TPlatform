//package org.tplatform.core.entity;
//
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Transient;
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 映射实体基础类
// * Created by Tianyi on 2014/12/3.
// */
//@Data
////@Entity
////@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
//public abstract class BaseEntity implements Serializable {
//
//  private static final long serialVersionUID = 1L;
//
//  @Id
//  @GeneratedValue
//  @Column(length = 20)
//  protected Long id;
//  // 创建时间
//  @Column(columnDefinition = "datetime", updatable = false)
//  protected Date createTime;
//  // 操作员
//  @Column(length = 20)
//  protected String operator;
//  // 状态
//  @Column(length = 20)
//  protected String status;
//  // 机构层级（数据权限）
//  @Transient
//  protected String hierarchy;
//  @Transient
//  protected String q;
//
//}
