package org.tplatform.tag;

import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.entity.SysUser;
import org.tplatform.auth.service.ISysResourceService;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.framework.log.Logger;
import org.tplatform.framework.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class MenuTag extends TagSupport {

  @Override
  public int doStartTag() throws JspException {
    List<SysResource> resources;
    StringBuilder roleId = new StringBuilder();
    StringBuilder html = new StringBuilder();
    SysUser user = (SysUser) SpringContextUtil.getSession().getAttribute(GlobalConstant.SESSION_USER_KEY);
    if (user != null && user.getRoles() != null) {
      user.getRoles().stream().forEach(sysRole -> roleId.append(",").append(sysRole.getId()));
    }
    if (roleId.length() > 0) {
      resources = SpringContextUtil.getBean(ISysResourceService.class).findMenuTree(roleId.substring(1), StatusEnum.VALID);
      if (resources != null && resources.size() > 0) {
        String path = SpringContextUtil.getRequest().getServletPath();
        resources.stream().forEach(resource -> {
          boolean hasChildren = resource.getChildren() != null && resource.getChildren().size() > 0;
          final boolean[] current = {false};
          StringBuffer subMenu = new StringBuffer();
          if (hasChildren) {
            subMenu.append("<ul class=\"sub-menu\">");
            resource.getChildren().stream().forEach(child -> {
              if (!current[0] && child.getAction().startsWith(path)) {
                current[0] = true;
                subMenu.append("<li class=\"nav-item active open\">")
                    .append("<a href=\"javascript:void(0);\" data-action=\"").append(child.getAction()).append("\" class=\"nav-link\">")
                    .append("<i class=\"fa fa-").append(child.getIcon()).append("\"></i><span class=\"title\">").append(child.getName()).append("</span></a></li>");

              } else {
                subMenu.append("<li class=\"nav-item\">")
                    .append("<a href=\"javascript:void(0);\" data-action=\"").append(child.getAction()).append("\" class=\"nav-link\">")
                    .append("<i class=\"fa fa-").append(child.getIcon()).append("\"></i><span class=\"title\">").append(child.getName()).append("</span></a></li>");

              }
            });
            subMenu.append("</ul>");
          }
          html.append("<li class=\"nav-item" + (current[0] ? " active open" : "") + "\">")
              .append("<a href=\"javascript:void(0);\" data-action=\"").append(resource.getAction()).append("\" class=\"nav-link nav-toggle\">")
              .append("<i class=\"fa fa-").append(resource.getIcon()).append("\"></i><span class=\"title\">").append(resource.getName()).append("</span>")
              .append(hasChildren ? "<span class=\"arrow\"></span>" : "").append("</a>").append(subMenu).append("</li>");
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
