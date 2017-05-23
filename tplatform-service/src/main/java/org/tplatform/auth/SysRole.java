package org.tplatform.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.tplatform.common.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Colin on 2017/4/26.
 */
@Setter
@Getter
@Entity
@Table(name = "sys_auth_role")
@DynamicInsert
@DynamicUpdate
public class SysRole extends BaseEntity {
  @Id
  @GeneratedValue(generator = "assigned")
  @GenericGenerator(name = "assigned", strategy = "assigned")
  @Column(length = 32)
  private String role;
  @Column(length = 32)
  private String name;
  @Column(length = 16)
  private String icon;
  @Column(length = 1)
  private Integer dataAuthType;// 授权类型(1:查看下级，2:查看同级，3:查看自己，4:禁止查看数据)


  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinTable(name = "sys_auth_role_resource", joinColumns = @JoinColumn(name = "role"),
      inverseJoinColumns = @JoinColumn(name = "resource_id"))
  private List<SysResource> resourceList;
}
