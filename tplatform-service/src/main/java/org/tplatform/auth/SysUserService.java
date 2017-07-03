package org.tplatform.auth;

import org.tplatform.common.BaseRepo;

import java.util.List;

/**
 * Created by Colin on 2016/3/18.
 */
public interface SysUserService extends BaseRepo<SysUser> {

  /**
   * 根据机构和状态查询用户
   * @param organId
   * @param status
   * @return
   */
  List<SysUser> findByOrganIdAndStatus(Long organId, Integer status);

  /**
   * 根据机构和状态查询用户
   * @param organIdList
   * @param status
   * @return
   */
  List<SysUser> findByOrganIdInAndStatus(List<Long> organIdList, Integer status);
}
