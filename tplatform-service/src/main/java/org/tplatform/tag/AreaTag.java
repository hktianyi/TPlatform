package org.tplatform.tag;

import lombok.Setter;
import org.tplatform.domain.Dict;
import org.tplatform.domain.DictService;
import org.tplatform.util.Logger;
import org.tplatform.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by tianyi on 2016/11/23.
 */
public class AreaTag extends TagSupport {

  @Setter
  private String html;

  @Override
  public int doStartTag() throws JspException {
    StringBuilder html_sbd = new StringBuilder();
    List<Dict> list = SpringContextUtil.getBean(DictService.class).findMunicipalityList();
    html = html.replace("{value}", "{0}").replace("{zhName}", "{1}");
    for (Dict dict : list) {
      html_sbd.append(MessageFormat.format(html, dict.getValue(), dict.getZhName()));
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
}
