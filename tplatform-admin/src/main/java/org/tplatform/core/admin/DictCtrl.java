package org.tplatform.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tplatform.common.BaseCtrl;
import org.tplatform.domain.Dict;
import org.tplatform.domain.DictTypeService;

/**
 * Created by Tianyi on 2016/3/21.
 */
@Controller
@RequestMapping("/dict")
public class DictCtrl extends BaseCtrl<Dict> {

  @Autowired
  private DictTypeService dictTypeService;

  @Override
  public String list(ModelMap modelMap) {
    modelMap.put("dicTypeList", dictTypeService.findAll());
    return super.list(modelMap);
  }
}
