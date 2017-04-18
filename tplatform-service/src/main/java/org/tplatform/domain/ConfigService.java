package org.tplatform.domain;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.tplatform.common.BaseRepo;
import org.tplatform.common.GlobalConstant;

import javax.transaction.Transactional;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ConfigService extends BaseRepo<Config> {

  /**
   * 根据key查询配置
   *
   * @param confKey 配置KEY
   * @return String
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Config_Val_' + #confKey")
  @Query(value = "SELECT val FROM sys_config WHERE confKey = ?1", nativeQuery = true)
  String getByKey(String confKey);

  /**
   * 更新配置项
   *
   * @param val 取值
   * @param confKey 配置KEY
   * @return String
   */
  @CachePut(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Config_Val_' + #confKey")
  @Transactional
  @Modifying
  @Query(value = "UPDATE Config set val = ?1 WHERE confKey = ?2")
  int updateByKey(String val, String confKey);
}
