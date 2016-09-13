package org.tplatform.sys.service;

import org.tplatform.core.service.IBaseService;
import org.tplatform.sys.entity.SysInfo;

/**
 * Created by Tianyi on 2016/6/2.
 */
public interface ISysInfoService extends IBaseService<SysInfo> {
  /**
   * 获得当前的监控对象.
   * @return 返回构造好的监控对象
   */
  SysInfo getSysInfo();
}
