package org.tplatform.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class ButtonTag extends TagSupport {

  private String parentCode;

  @Override
  public int doStartTag() throws JspException {



    return super.doStartTag();
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }
}
