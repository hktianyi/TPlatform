package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 组织信息
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Table(name = "sys_auth_organ")
public class SysOrgan extends BaseEntity {

  private String code;// 机构编码
  private String pCode;// 父编码
  private String type;// 机构类型
  private String name;// 机构名
  private String nickname; // 别名
  private Integer sort;    // 排序号
  @Transient
  private Integer leaf;// 叶子节点
}
