package org.tplatform.domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.tplatform.common.BaseRepo;
import org.tplatform.common.GlobalConstant;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ConfigService extends BaseRepo<Config> {

  /**
   * 根据key查询配置
   *
   * @param confKey
   * @return
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Config_Val_' + #confKey")
  @Query(value = "SELECT val FROM config WHERE confKey = ?1", nativeQuery = true)
  String getByKey(String confKey);
}
