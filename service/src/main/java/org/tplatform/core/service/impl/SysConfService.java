package org.tplatform.core.service.impl;

import org.tplatform.core.mapper.SysConfMapper;
import org.tplatform.core.service.ISysConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tplatform.framework.util.StringUtil;

/**
 * Created by Tianyi on 2015/1/6.
 */
@Service
public class SysConfService implements ISysConfService {

  @Autowired
  private SysConfMapper sysConfMapper;

  /**
   * 根据key查询配置
   *
   * @param confKey
   * @return
   */
  @Cacheable(value = "_SYS", key="'_Conf' + #confKey")
  public String findVal(String confKey) {
    String val = null;
    if (StringUtil.isNotEmpty(confKey)) {
      val = sysConfMapper.findVal(confKey);
    }
    return val;
  }
//
//  /**
//   * 根据key查询配置
//   *
//   * @param confKey
//   * @return
//   */
//  @Cacheable(value="sysConfCache", key="#confKey")
//  public String findRemark(String confKey) {
//    String val = null;
//    if (StringUtil.isNotEmpty(confKey)) {
//      val = (String) super.excuteOne("select remark from sys_conf where confKey = '" + confKey + "'");
//    }
//    return val;
//  }
//
//  /**
//   * 更新缓存
//   *
//   * @param confKey
//   * @param confVal
//   * @return
//   */
//  @CacheEvict(value="sysConfCache", key="#confKey")
//  public void update(String confKey, String confVal) {
//    if (StringUtil.isNotEmpty(confKey)) {
//      super.executeUpdate("update sys_conf set val = '" + confVal + "' where confKey = '" + confKey + "'");
//    }
//  }

}
