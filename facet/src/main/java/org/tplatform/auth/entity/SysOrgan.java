package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Table;

/**
 * 组织信息
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Table(name = "sys_auth_organ")
public class SysOrgan extends BaseEntity {

  private String organCode;// 机构编码
  private String parentCode;// 父编码
  private String organType;// 机构类型
  private String organName;// 机构名
  private String nickname; // 别名
  private Integer sort;    // 排序号
  private boolean isLast;  // 是否末级
}
