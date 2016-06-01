package org.tplatform.common;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.core.entity.BaseEntity;
import org.tplatform.core.entity.RespBody;
import org.tplatform.core.service.IBaseService;
import org.tplatform.framework.log.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 基础业务控制器
 * Created by Tianyi on 2014/11/20.
 */
public abstract class BaseCtrl<E extends BaseEntity> {
  /**
   * 模版路径名称
   */
  protected final String dir;
  protected final String moduleName;

  @Autowired
  protected HttpServletRequest request;
  @Autowired
  protected HttpSession session;
  @Autowired
  protected IBaseService<E> baseService;

  protected BaseCtrl() {
    Type type = this.getClass().getGenericSuperclass();
    if (type instanceof ParameterizedType) {
      type = ((ParameterizedType) type).getActualTypeArguments()[0];
      String[] pack = type.getTypeName().split("\\.");
      dir = "/" + pack[pack.length - 3];
      moduleName = StringUtils.uncapitalize(pack[pack.length - 1]);
    } else {
      dir = "/";
      moduleName = "";
    }
  }

  /**
   * 默认列表页
   *
   * @return
   */
  @RequestMapping
  public String init(ModelMap modelMap) {
    request.setAttribute(GlobalConstant.APP_MODULE_NAME, moduleName);
    this.list(modelMap);
    return this.dir + "/" + this.moduleName + ".jsp";
  }


  //******************************** CURD公共方法 *******************************

  /**
   * Ajax加载数据
   *
   * @param e 业务实体
   * @param pageInfo 分页信息
   * @param start 页码
   * @param length 每页显示记录数
   * @return
   */
  @RequestMapping("/load")
  @ResponseBody
  public RespBody load(E e, PageInfo pageInfo, Integer start, Integer length) {
    pageInfo.setStartRow(start);
    pageInfo.setPageSize(length);
    return RespBody.ok(baseService.find(e, pageInfo));
  }

  /**
   * 默认列表页
   *
   * @return
   */
  @RequestMapping("/list")
  public String list(ModelMap modelMap) {
    request.setAttribute(GlobalConstant.APP_MODULE_NAME, moduleName);
    this.listHook(modelMap);
    return this.dir + "/" + this.moduleName + "List.jsp";
  }

  protected void listHook(ModelMap modelMap) {
  }

  /**
   * 编辑、详情页
   *
   * @param id 主键
   * @return
   */
  @RequestMapping("/edit")
  public String edit(@RequestParam(value = "id", required = false) Long id, ModelMap modelMap) {
    request.setAttribute(GlobalConstant.APP_MODULE_NAME, moduleName);
    this.editHook(id, modelMap);
    return this.dir + "/" + this.moduleName + "Edit.jsp";
  }

  protected void editHook(Long id, ModelMap modelMap) {
    if (id != null) modelMap.put("data", baseService.find(id));
  }

  /**
   * 保存数据
   *
   * @param e 实体对象
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public RespBody save(E e) {
    baseService.saveOrUpdate(e);
    return RespBody.ok();
  }

  /**
   * 删除数据
   *
   * @param id 主键
   * @return
   */
  @RequestMapping("/delete/{id}")
  @ResponseBody
  public RespBody delete(@PathVariable(value = "id") Long id) {
    if (baseService.delete(id) > 0) return RespBody.ok("删除成功！");
    else return RespBody.error("删除失败，没找到对应数据！");
  }

  /**
   * 统一异常处理
   *
   * @param e 异常信息
   * @return
   */
  @ExceptionHandler(Exception.class)
  public String exceptionHandler(Exception e) {
    Logger.e(this.getClass(), "统一异常处理", e);
    return dir.concat("/error/500.html");
  }
}
