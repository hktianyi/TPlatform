package org.tplatform.tag;

import lombok.Setter;
import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.entity.SysUser;
import org.tplatform.auth.service.SysResourceService;
import org.tplatform.common.GlobalConstant;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.util.Logger;
import org.tplatform.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class MenuTag extends TagSupport {

  @Setter
  private String type;
  @Setter
  private Long parentCode;

  // 纵向菜单
  private static final String[] menu_V = {"<ul class=\"nav navbar-nav\">{0}</ul>", "<li class=\"classic-menu-dropdown\"><a href=\"{0}\" data-hover=\"megamenu-dropdown\" data-close-others=\"true\"> {1} </a></li>",
      "<ul class=\"dropdown-menu pull-left\">{0}</ul>", "<li><a href=\"{0}\"><i class=\"fa fa-bookmark-o\"></i> {1} </a></li>"};
  // 横向菜单
  private static final String[] menu_H = {"<ul class=\"nav navbar-nav\">{0}</ul>",
      "<li class=\"classic-menu-dropdown {0}\"><a href=\"{1}\" data-hover=\"megamenu-dropdown\" data-close-others=\"true\"> {2} </a>{3}</li>",
      "<ul class=\"dropdown-menu pull-left\">{0}</ul>",
      "<li class=\"{0}\"><a href=\"{1}\"><i class=\"fa fa-{2}\"></i> {3} </a></li>"};

  @Override
  public int doStartTag() throws JspException {
    List<SysResource> resources;
    StringBuilder roleId = new StringBuilder();
    StringBuilder html = new StringBuilder();
    SysUser user = (SysUser) SpringContextUtil.getSession().getAttribute(GlobalConstant.KEY_SESSION_USER);
    if (user != null && user.getRoles() != null) {
      user.getRoles().stream().forEach(sysRole -> roleId.append(",").append(sysRole.getId()));
    }
    if (roleId.length() > 0) {
      resources = SpringContextUtil.getBean(SysResourceService.class).findMenuTree(roleId.substring(1), StatusEnum.INIT, parentCode);
      if (resources != null && resources.size() > 0) {
        String path = SpringContextUtil.getRequest().getServletPath();
        String contextPath = SpringContextUtil.getRequest().getContextPath();
        resources.stream().forEach(resource -> {
          boolean hasChildren = resource.getChildren() != null && resource.getChildren().size() > 0;
          String subMenuStr = "";
          final boolean[] active = {false}; // 活跃菜单
          if (hasChildren) {
            StringBuffer subMenu = new StringBuffer();
            resource.getChildren().stream().forEach(child -> {
              if(child.getAction().startsWith(path)) active[0] = true;
              subMenu.append(MessageFormat.format(menu_H[3], active[0] ? "active" : "", contextPath + child.getAction(), child.getIcon(), child.getName()));
            });
            subMenuStr = MessageFormat.format(menu_H[2], subMenu);
          }
          active[0] = active[0] || resource.getAction().startsWith(path);
          html.append(MessageFormat.format(menu_H[1], active[0] ? "active" : "", contextPath + resource.getAction(), resource.getName() + (active[0] ? "<span class=\"selected\"> </span>" : ""), subMenuStr));
        });
      }
    }

    try {
      this.pageContext.getOut().print(html.toString());
    } catch (IOException e) {
      Logger.e("MenuTag", e);
    }
    return SKIP_BODY;
  }
}
