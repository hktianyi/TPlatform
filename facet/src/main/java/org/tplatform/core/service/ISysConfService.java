package org.tplatform.core.service;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ISysConfService {
  /**
   * 根据key查询配置
   *
   * @param confKey
   * @return
   */
  public String findVal(String confKey);
}
