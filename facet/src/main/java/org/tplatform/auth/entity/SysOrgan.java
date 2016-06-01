package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 组织信息
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Table(name = "sys_auth_organ")
public class SysOrgan extends BaseEntity {

  @Column(length = 16)
  private String organCode;// 机构编码
  @Column(length = 16)
  private String parentCode;// 父编码
  @Column(length = 6)
  private String organType;// 机构类型
  @Column(length = 32)
  private String organName;// 机构名
  @Column(length = 10)
  private String nickname; // 别名
  private Integer sort;    // 排序号
  private boolean isLast;  // 是否末级
}
