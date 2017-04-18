package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.auth.SysRoleService;
import org.tplatform.auth.SysUser;
import org.tplatform.auth.SysUserService;
import org.tplatform.common.BaseCtrl;
import org.tplatform.common.GlobalConstant;
import org.tplatform.common.RespBody;
import org.tplatform.util.StringUtil;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserCtrl extends BaseCtrl<SysUser> {

  @Autowired
  private SysUserService sysUserService;
  @Autowired
  private SysRoleService sysRoleService;

  @Override
  protected void editHook(Long id, ModelMap modelMap) {
    modelMap.put("roles", sysRoleService.findAll());
    if (id != null) {
      SysUser sysUser = sysUserService.findOne(id);
      sysUser.setRoles(sysRoleService.findByUserId(sysUser.getId()));
      modelMap.put("data", sysUser);
    }
  }

  @Override
  protected void listHook(ModelMap modelMap) {
    modelMap.put("roles", sysRoleService.findAll());
    super.listHook(modelMap);
  }

  @RequestMapping("/loadByRole")
  @ResponseBody
  public RespBody loadByRole(SysUser e, Integer start, Integer length, Long roleId) {
    Page page;
    if (StringUtil.isNotEmpty(e.getQ()) && StringUtil.isNotEmpty(e.getQNames())) {
      page = baseService.findAll((root, query, cb) -> {
        List<Predicate> andList = new ArrayList<>();
        List<Predicate> orList = new ArrayList<>();
        String q = "%" + e.getQ() + "%";
        String[] qNames = e.getQNames().split(",");
        for (String qName : qNames) {
          orList.add(cb.like(root.get(qName), q));
        }
        if (orList.size() > 0) {
          andList.add(cb.or(orList.toArray(new Predicate[orList.size()])));
        }
        if (roleId != null && roleId > 0) {
          andList.add(cb.equal(root.get("roles.id"), roleId));
        }
        return cb.and(andList.toArray(new Predicate[andList.size()]));
      }, new PageRequest(start / length, length, new Sort(Sort.Direction.DESC, "id")));
    } else {
      page = baseService.findAll(new PageRequest(start / length, length, new Sort(Sort.Direction.DESC, "id")));
    }
    return RespBody.ok(page);
  }

  /**
   * 账户信息配置
   *
   * @return
   */
  @RequestMapping("/profile")
  public String profile(String type, ModelMap modelMap) {
    modelMap.put("type", type);
    return super.dir + "/profile.jsp";
  }

  /**
   * 账户信息配置
   *
   * @return
   */
  @RequestMapping("/checkOldPwd")
  @ResponseBody
  public boolean checkOldPwd(@RequestParam String oldPwd) {
    SysUser sysUser = (SysUser) session.getAttribute(GlobalConstant.KEY_SESSION_USER);
    return oldPwd.equals(sysUser.getPassword());
  }

  /**
   * 账户信息配置
   *
   * @return
   */
  @RequestMapping("/updateAccount")
  @ResponseBody
  public RespBody updateAccount(@RequestParam(required = false) String nickname, @RequestParam(required = false) String mobile, @RequestParam(required = false) String email) {
    SysUser sysUser = (SysUser) session.getAttribute(GlobalConstant.KEY_SESSION_USER);
    if (StringUtil.isNotEmpty(nickname)) sysUser.setNickname(nickname);
    if (StringUtil.isNotEmpty(mobile)) sysUser.setMobile(mobile);
    if (StringUtil.isNotEmpty(email)) sysUser.setEmail(email);

    sysUserService.save(sysUser);
    session.setAttribute(GlobalConstant.KEY_SESSION_USER, sysUser);
    return RespBody.ok();
  }

  /**
   * 账户信息配置
   *
   * @return
   */
  @RequestMapping("/updatePwd")
  @ResponseBody
  public RespBody updatePwd(@RequestParam String oldPwd, @RequestParam String newPwd) {
    if (checkOldPwd(oldPwd)) {
      SysUser sysUser = (SysUser) session.getAttribute(GlobalConstant.KEY_SESSION_USER);
      sysUser.setPassword(newPwd);

      sysUserService.save(sysUser);
      session.removeAttribute(GlobalConstant.KEY_SESSION_USER);
      return RespBody.ok();
    } else {
      //TODO 错误处理
//      return RespBody.error("当前密码错误");
      return null;
    }
  }

//  /**
//   * 保存, status如果为空,则设置为无效(INVALID)
//   * @param sysUser
//   * @return
//   */
//  @RequestMapping(value = "/saveWithRole", method = RequestMethod.POST)
//  @ResponseBody
//  public RespBody saveWithRole(SysUser sysUser, @RequestParam("role[]") Long[] roles) {
//    if(sysUser.getStatus() == null) sysUser.setStatus(StatusEnum.INVALID.getCode());
//    return RespBody.ok(sysUserService.saveWithRole(sysUser, roles));
//  }
}
