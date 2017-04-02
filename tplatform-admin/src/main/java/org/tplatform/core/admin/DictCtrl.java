package org.tplatform.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tplatform.common.BaseCtrl;
import org.tplatform.domain.Dict;
import org.tplatform.domain.DictService;
import org.tplatform.domain.DictTypeService;

/**
 * Created by Tianyi on 2016/3/21.
 */
@Controller
@RequestMapping("/dict")
public class DictCtrl extends BaseCtrl<Dict> {

  @Autowired
  private DictService dictService;
  @Autowired
  private DictTypeService dictTypeService;

  @Override
  protected void listHook(ModelMap modelMap) {
    modelMap.put("dicTypeList", dictTypeService.findAll());
    super.listHook(modelMap);
  }
}
