package org.tplatform.domain;

import lombok.Getter;

/**
 * Created by tianyi on 2016/12/21.
 */
public enum AreaType {
  
  行政区划("area_code"),省(1),市(2),区县(3),直辖市(4);

  @Getter
  private String code;
  @Getter
  private Integer status;

  AreaType(String code) {
    this.code = code;
  }
  AreaType(Integer status) {
    this.status = status;
  }
}
