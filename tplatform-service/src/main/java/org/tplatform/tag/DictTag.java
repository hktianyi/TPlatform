package org.tplatform.tag;

import lombok.Setter;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.tplatform.domain.Dict;
import org.tplatform.domain.DictService;
import org.tplatform.util.Logger;
import org.tplatform.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by tianyi on 2016/11/23.
 */
public class DictTag extends TagSupport {

  @Setter
  private String type;
  @Setter
  private String key;
  @Setter
  private String name;
  @Setter
  private String className;
  @Setter
  private String attr;
  private Object value;

  @Override
  public int doStartTag() throws JspException {
    StringBuilder html_sbd = new StringBuilder();
    List<Dict> list = SpringContextUtil.getBean(DictService.class).findByDicType(key);

    if ("select".equalsIgnoreCase(type)) {
      html_sbd.append(String.format("<select name=\"%s\" class=\"%s\" %s><option value=\"\"></option>", name, className, attr));
      if (list != null && list.size() > 0) {
        list.stream().forEach(o -> html_sbd.append(String.format("<option value=\"%s\"%s>%s</option>", o.getValue(),
            value != null && value.equals(o.getValue()) ? " selected" : "", o.getZhName())));
      }
      html_sbd.append("</select>");
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

  public void setValue(Object value) {
    try {
      this.value = ExpressionEvaluatorManager.evaluate("value", value.toString(), Object.class, this, pageContext);
    } catch (JspException e) {
      Logger.e("[自定义标签]DictTag.setValue异常", e);
    }
  }
}
