package org.tplatform.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.common.BaseCtrl;
import org.tplatform.common.RespBody;
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

  // --------------------------------字典--------------------------------------
  @RequestMapping("/list/{menuType}")
  public String list(@PathVariable String menuType, ModelMap modelMap) {
    modelMap.put("menuType", menuType);
//    modelMap.put("DictTypes", dictTypeService.findAll(menuType));
    modelMap.put("body", dir + "/DictList.jsp");
    return dir + "/DictList.jsp";
  }

  @RequestMapping("/findDict")
  @ResponseBody
  public RespBody findDict(Dict Dict) {
    return RespBody.ok(dictService.findAll(Example.of(Dict)));
  }

  // 修改
  @RequestMapping("/editDict")
  public String editDict(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "dicTypeId", required = false) Long dicTypeId, ModelMap modelMap) {
    if (id != null) modelMap.put("data", dictService.findOne(id));
    else modelMap.put("dicTypeId", dicTypeId);
    return dir + "/Dict.jsp";
  }

  // 保存
  @RequestMapping("/saveDict")
  @ResponseBody
  public RespBody saveDict(Dict dict) {
//    dict.setCreateTime(DateUtil.getCurrentDate());
//    dict.setOperator(SpringContextUtil.getOperator());
//    if (dict.getId() != null) dictService.save(dict);
//    else
    dictService.save(dict);
    return RespBody.ok();
  }

  // 删除
  @RequestMapping(value = "/deleteDict", method = RequestMethod.DELETE)
  @ResponseBody
  public RespBody deleteDict(Long id, ModelMap modelMap) {
    if (id != null) dictService.delete(id);
    return RespBody.ok();
  }


  // ----------------------------------字典类型--------------------------------
//  // 加载
//  @RequestMapping("/findDictType")
//  @ResponseBody
//  public RespBody findDictType(DictType DictType) {
//    return RespBody.ok(DictTypeService.find(DictType));
//  }
//
//  // 修改
//  @RequestMapping("/editDictType/{menuType}")
//  public String editDictType(@PathVariable String menuType, @RequestParam(value = "id", required = false) Long id, ModelMap modelMap) {
//    modelMap.put("menuType", menuType);
//    if (id != null) modelMap.put("data", DictTypeService.find(id));
//    return dir + "/DictType.jsp";
//  }
//
//  // 保存
//  @RequestMapping("/saveDictType/{menuType}")
//  @ResponseBody
//  public RespBody saveDictType(@PathVariable String menuType, DictType DictType) {
//    DictType.setMenuType(menuType);
//    DictType.setCreateTime(DateUtil.getCurrentDate());
//    DictType.setOperator(SpringContextUtil.getOperator());
//    if (DictType.getId() != null) DictTypeService.update(DictType);
//    else DictTypeService.save(DictType);
//    return RespBody.ok();
//  }
//
//  // 删除
//  @RequestMapping(value = "/deleteDictType", method = RequestMethod.DELETE)
//  @ResponseBody
//  public RespBody deleteDictType(Long id, ModelMap modelMap) {
//    if (id != null) modelMap.put("data", DictTypeService.delete(id));
//    return RespBody.ok();
//  }

}
