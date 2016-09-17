package org.tplatform.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.core.entity.SysConf;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.core.mapper.SysConfMapper;
import org.tplatform.core.service.ISysConfService;
import org.tplatform.framework.util.DateUtil;
import org.tplatform.framework.util.SpringContextUtil;
import org.tplatform.framework.util.StringUtil;
import org.tplatform.impl.BaseService;

/**
 * Created by Tianyi on 2015/1/6.
 */
@Service
public class SysConfService extends BaseService<SysConf> implements ISysConfService {

  @Autowired
  private SysConfMapper sysConfMapper;

  /**
   * 根据key查询配置
   *
   * @param confKey
   * @return
   */
  @Override
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Conf' + #confKey")
  public String findVal(String confKey) {
    String val = null;
    if (StringUtil.isNotEmpty(confKey)) {
      val = sysConfMapper.findVal(confKey);
    }
    return val;
  }

  @Override
  @CacheEvict(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Conf' + #confKey")
  public boolean saveVal(String confKey, String value) {
    Assert.hasText(confKey);
    Assert.hasText(value);

    if (sysConfMapper.updateVal(confKey, value) > 0) return true;

    // 更新失败，则保存
    SysConf sysConf = new SysConf();
    sysConf.setConfKey(confKey);
    sysConf.setVal(value);
    sysConf.setOperator(SpringContextUtil.getOperator());
    sysConf.setCreateTime(DateUtil.getCurrentDate());
    sysConf.setStatus(StatusEnum.VALID);
    return sysConfMapper.insert(sysConf) > 0;
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
