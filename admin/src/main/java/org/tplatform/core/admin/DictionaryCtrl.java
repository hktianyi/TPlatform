package org.tplatform.core.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.common.BaseCtrl;
import org.tplatform.core.entity.Dictionary;
import org.tplatform.core.entity.DictionaryType;
import org.tplatform.core.entity.RespBody;
import org.tplatform.core.service.IDictionaryService;
import org.tplatform.core.service.IDictionaryTypeService;
import org.tplatform.framework.util.DateUtil;
import org.tplatform.framework.util.SpringContextUtil;

/**
 * Created by Tianyi on 2016/3/21.
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryCtrl extends BaseCtrl<Dictionary> {

  @Autowired
  private IDictionaryService dictionaryService;
  @Autowired
  private IDictionaryTypeService dictionaryTypeService;

  // --------------------------------字典--------------------------------------
  @RequestMapping("/list/{menuType}")
  public String list(@PathVariable String menuType, ModelMap modelMap) {
    modelMap.put("menuType", menuType);
    modelMap.put("dictionaryTypes", dictionaryTypeService.findByMenuType(menuType));
    modelMap.put("body", dir + "/dictionaryList.jsp");
    return dir + "/dictionaryList.jsp";
  }

  @RequestMapping("/findDictionary")
  @ResponseBody
  public RespBody findDictionary(Dictionary dictionary) {
    return RespBody.ok(dictionaryService.find(dictionary));
  }

  // 修改
  @RequestMapping("/editDictionary")
  public String editDictionary(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "dicTypeId", required = false) Long dicTypeId, ModelMap modelMap) {
    if (id != null) modelMap.put("data", dictionaryService.find(id));
    else modelMap.put("dicTypeId", dicTypeId);
    return dir + "/dictionary.jsp";
  }

  // 保存
  @RequestMapping("/saveDictionary")
  @ResponseBody
  public RespBody saveDictionary(Dictionary dictionary) {
    dictionary.setTimestamp(DateUtil.getTimeInMillis());
    dictionary.setOperator(SpringContextUtil.getOperator());
    if (dictionary.getId() != null) dictionaryService.update(dictionary);
    else dictionaryService.save(dictionary);
    return RespBody.ok();
  }

  // 删除
  @RequestMapping(value = "/deleteDictionary", method = RequestMethod.DELETE)
  @ResponseBody
  public RespBody deleteDictionary(Long id, ModelMap modelMap) {
    if (id != null) modelMap.put("data", dictionaryService.delete(id));
    return RespBody.ok();
  }


  // ----------------------------------字典类型--------------------------------
  // 加载
  @RequestMapping("/findDictionaryType")
  @ResponseBody
  public RespBody findDictionaryType(DictionaryType dictionaryType) {
    return RespBody.ok(dictionaryTypeService.find(dictionaryType));
  }

  // 修改
  @RequestMapping("/editDictionaryType/{menuType}")
  public String editDictionaryType(@PathVariable String menuType, @RequestParam(value = "id", required = false) Long id, ModelMap modelMap) {
    modelMap.put("menuType", menuType);
    if (id != null) modelMap.put("data", dictionaryTypeService.find(id));
    return dir + "/dictionaryType.jsp";
  }

  // 保存
  @RequestMapping("/saveDictionaryType/{menuType}")
  @ResponseBody
  public RespBody saveDictionaryType(@PathVariable String menuType, DictionaryType dictionaryType) {
    dictionaryType.setMenuType(menuType);
    dictionaryType.setTimestamp(DateUtil.getTimeInMillis());
    dictionaryType.setOperator(SpringContextUtil.getOperator());
    if (dictionaryType.getId() != null) dictionaryTypeService.update(dictionaryType);
    else dictionaryTypeService.save(dictionaryType);
    return RespBody.ok();
  }

  // 删除
  @RequestMapping(value = "/deleteDictionaryType", method = RequestMethod.DELETE)
  @ResponseBody
  public RespBody deleteDictionaryType(Long id, ModelMap modelMap) {
    if (id != null) modelMap.put("data", dictionaryTypeService.delete(id));
    return RespBody.ok();
  }

}
