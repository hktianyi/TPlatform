package org.tplatform.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 业务实体类基类
 * Created by tianyi on 2016/11/18.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @JsonIgnore
  @Transient
  private String q;//搜索关键字
  @JsonIgnore
  @Transient
  private String qNames;//搜索关键字
}
