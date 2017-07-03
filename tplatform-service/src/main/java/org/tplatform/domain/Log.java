package org.tplatform.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.tplatform.common.BaseEntity;
import org.tplatform.util.DateUtil;
import org.tplatform.util.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

/**
 * Created by Tianyi on 2015/12/13.
 */
@Data
@Entity
@Table(name = "sys_log")
@DynamicUpdate
@DynamicInsert
public class Log extends BaseEntity {

  // 日志类型（1：接入日志；2：错误日志）
  public static final int TYPE_ACCESS = 1;
  public static final int TYPE_EXCEPTION = 2;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  private Long id;
  @Column(length = 1)
  private Integer type;    // 日志类型（1：接入日志；2：错误日志）
  @Column(length = 64)
  private String title;    // 日志标题
  @Column(length = 32)
  private String remoteAddr;  // 操作用户的IP地址
  @Column(length = 200)
  private String requestUri;  // 操作的URI
  @Column(length = 8)
  private String method;    // 操作的方式
  @Column(length = 200)
  private String params;    // 操作提交的数据
  @Column(length = 200)
  private String userAgent;  // 操作用户代理信息
  @Column(length = 500)
  private String exception;  // 异常信息
  @Column(length = 128)
  private String operator;  // 用户名

  @Column(columnDefinition = "datetime")
  private Date createTime;    // 创建日期

  /**
   * 设置请求参数
   *
   * @param paramMap
   */
  public void setParams(Map paramMap) {
    if (paramMap == null) {
      return;
    }
    StringBuilder params = new StringBuilder();
    for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
      params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
      params.append((param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : ""));
    }
    this.params = StringUtil.abbr(params.toString(), 200);
  }

  @PrePersist
  public void prePersist() {
    this.createTime = DateUtil.getCurrentDate();
  }
}