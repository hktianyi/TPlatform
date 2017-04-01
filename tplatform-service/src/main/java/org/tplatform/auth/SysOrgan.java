package org.tplatform.auth;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 组织信息
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Entity
@Table(name = "SYS_AUTH_ORGAN")
public class SysOrgan extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  private Long id;

  @Column(length = 16, nullable = false, unique = true)
  private String code;// 机构编码
  @Column(length = 16)
  private String pCode;// 父编码
  @Column(length = 8)
  private String type;// 机构类型
  @Column(length = 32)
  private String name;// 机构名
  @Column(length = 32)
  private String nickname; // 别名
  @Column(length = 4)
  private Integer sort;    // 排序号
  @Transient
  private Integer leaf;// 叶子节点
}
