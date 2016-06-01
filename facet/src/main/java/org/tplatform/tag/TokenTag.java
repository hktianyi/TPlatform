package org.tplatform.tag;

import org.tplatform.token.TokenType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class TokenTag extends TagSupport {

  private TokenType type = TokenType.UUID;
  private String key = "";

  @Override
  public int doStartTag() throws JspException {
    return EVAL_BODY_INCLUDE;
  }

  public void setType(TokenType type) {
    this.type = type;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
