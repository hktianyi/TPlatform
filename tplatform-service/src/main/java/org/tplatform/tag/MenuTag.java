package org.tplatform.tag;

import lombok.Setter;
import org.tplatform.auth.SysResource;
import org.tplatform.auth.SysResourceService;
import org.tplatform.auth.SysUser;
import org.tplatform.auth.SysUserService;
import org.tplatform.common.StatusEnum;
import org.tplatform.util.Logger;
import org.tplatform.util.SessionUtil;
import org.tplatform.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class MenuTag extends TagSupport {

  @Setter
  private String type;
  @Setter
  private Long parentCode;
  @Setter
  private String template;
  @Setter
  private String arrow;

  @Override
  public int doStartTag() throws JspException {
    List<SysResource> resources;
    StringBuilder roleId = new StringBuilder();
    StringBuilder html = new StringBuilder();
    SysUser user = SpringContextUtil.getBean(SysUserService.class).findByUsername(SessionUtil.getAuthenticatedUsername());
    if (user != null && user.getRoles() != null) {
      user.getRoles().stream().forEach(sysRole -> roleId.append(",").append(sysRole.getId()));
    }
    if (roleId.length() > 0) {
      resources = SpringContextUtil.getBean(SysResourceService.class).findMenuTree(roleId.substring(1), StatusEnum.INIT, parentCode);
      if (resources != null && resources.size() > 0) {
        String[] htmlTemplate = template.split(",");
        String path = SpringContextUtil.getRequest().getServletPath();
        String contextPath = SpringContextUtil.getRequest().getContextPath();
        resources.stream().forEach(resource -> {
          boolean hasChildren = resource.getChildren() != null && resource.getChildren().size() > 0;
          String subMenuStr = "";
//          final boolean[] active = {false}; // 活跃菜单
          if (hasChildren) {
            StringBuffer subMenu = new StringBuffer();
            resource.getChildren().stream().forEach(child -> {
//              if(child.getAction().startsWith(path)) active[0] = true;
              subMenu.append(String.format(htmlTemplate[2], contextPath + child.getAction(), child.getIcon(), child.getName()));
            });
            subMenuStr = String.format(htmlTemplate[1], subMenu);
          }
//          active[0] = active[0] || resource.getAction().startsWith(path);
          html.append(String.format(htmlTemplate[0], contextPath + resource.getAction(), resource.getIcon(), resource.getName(), (subMenuStr != "" ? arrow : ""), subMenuStr));
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
