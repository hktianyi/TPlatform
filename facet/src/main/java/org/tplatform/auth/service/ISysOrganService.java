package org.tplatform.auth.service;

import org.tplatform.auth.entity.SysOrgan;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.core.service.IBaseService;

import java.util.List;

/**
 * Created by Tianyi on 2016/1/10.
 */
public interface ISysOrganService extends IBaseService<SysOrgan> {

  /**
   * 查询jstree
   *
   * @param pCode
   * @param selectedIds
   * @param status
   * @return
   */
  List findForTree(String pCode, StatusEnum status, String selectedIds, Class<?> clazz);
}
