package org.tplatform.tag;

import lombok.Setter;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.tplatform.domain.Dict;
import org.tplatform.domain.DictService;
import org.tplatform.util.Logger;
import org.tplatform.util.SpringContextUtil;
import org.tplatform.util.StringUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by tianyi on 2016/11/23.
 */
public class DictTag extends TagSupport {

  @Setter
  private String type; // 元素类型（select|checkbox|radio）
  @Setter
  private String key;  // 字典key
  @Setter
  private String id; // html属性
  @Setter
  private String name; // html属性
  @Setter
  private String className; // html属性
  @Setter
  private String attr; // html自定义属性
  @Setter
  private String defaultOpton; // 默认option
  @Setter
  private boolean pid; // 父ID，级联选择时使用
  //  @Setter
//  private String option;
  private Object value; // 选中值

  @Override
  public int doStartTag() throws JspException {
    StringBuilder html_sbd = new StringBuilder();
    List<Dict> list = SpringContextUtil.getBean(DictService.class).findByDicType(key);

    // 下拉框
    if ("select".equalsIgnoreCase(type)) {
      html_sbd.append(String.format("<select name=\"%s\" class=\"%s\" %s>", name, className, attr));
      if (null != defaultOpton) {
        html_sbd.append(String.format("<option value=\"\">%s</option>", defaultOpton));
      }
      if (list != null && list.size() > 0) {
        list.stream().forEach(o -> html_sbd.append(String.format("<option value=\"%s\"%s %s>%s</option>", o.getValue(),
            pid ? " pid=\"" + o.getId() + "\"" : "", value != null && value.equals(o.getValue()) ? " selected" : "", o.getZhName())));
      }
      html_sbd.append("</select>");
      // 静态展示
    } else if ("view".equalsIgnoreCase(type)) {
      list.parallelStream().filter(dict -> dict.getValue().equals(value)).forEach(dict -> html_sbd.append(",").append(dict.getZhName()));
      if (html_sbd.length() > 0) {
        html_sbd.deleteCharAt(0);
      }
      // 列表
    } else if ("list".equalsIgnoreCase(type)) {
      if (StringUtil.isNotEmpty(className)) {
        list.stream().forEach(dict -> html_sbd.append(String.format(className, dict.getValue(), dict.getZhName())));
      }
      // JS字典
    } else if ("js".equalsIgnoreCase(type)) {
      html_sbd.append("var ").append(StringUtil.isEmpty(name) ? key : name).append(" = {");
      if (list.size() > 0) {
        list.stream().forEach(dict -> html_sbd.append(dict.getValue()).append(":'").append(dict.getZhName()).append("',"));
        int length = html_sbd.length();
        html_sbd.replace(length-1, length,"};");
      }
    } else if ("checkbox".equalsIgnoreCase(type)) {
      // todo 复选框
    } else if ("radio".equalsIgnoreCase(type)) {
      // todo 单选框
    }

    try {
      this.pageContext.getOut().print(html_sbd.toString());
    } catch (IOException e) {
      Logger.e("AreaTag", e);
    }
    return SKIP_BODY;
  }

  /**
   * 编码转文字
   *
   * @param id
   * @return
   */
  public static String getNameById(Long id) {
    String name = null;
    Dict dict = SpringContextUtil.getBean(DictService.class).findOne(id);
    if (dict != null)
      name = dict.getZhName();
    return name;
  }

//  private String getAttrs(Dict dict) {
//    String[] attrs = null;
//    if (StringUtil.isEmpty(option)) {
//      return "";
//    }
//    attrs = option.split("\\|");
//    StringBuilder sbd = new StringBuilder();
//    for (String attr : attrs) {
//      sbd.append(attr).append("=\"").append(dict == null ? "" : dict.getId()).append("\" ");
//    }
//    return sbd.toString();
//  }

  public void setValue(Object value) {
    if (value != null) {
      try {
        this.value = ExpressionEvaluatorManager.evaluate("value", value.toString(), Object.class, this, pageContext);
      } catch (JspException e) {
        Logger.e("[自定义标签]DictTag.setValue异常", e);
      }
    }
  }
}
