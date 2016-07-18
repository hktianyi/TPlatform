package org.tplatform.core.service;

import org.tplatform.core.entity.SysConf;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ISysConfService extends IBaseService<SysConf> {
  /**
   * 根据key查询配置
   *
   * @param confKey
   * @return
   */
  String findVal(String confKey);

  /**
   * 更新KEY
   * @param confKey
   * @param value
   * @return
   */
  boolean saveVal(String confKey, String value);
}
