package org.tplatform.common;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by tianyi on 2016/11/18.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Transient
  private String q;//搜索关键字
  @Transient
  private String qNames;//搜索关键字
}
