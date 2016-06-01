package org.tplatform.tag;

import org.tplatform.core.service.ISysConfService;
import org.tplatform.framework.log.Logger;
import org.tplatform.framework.util.SpringContextUtil;
import org.tplatform.framework.util.StringUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class ConfTag extends TagSupport {

  private static final ISysConfService sysConfService = SpringContextUtil.getBean(ISysConfService.class);
  private String key;
  private String showType;
  @Override
  public int doStartTag() throws JspException {
    try {
    	 String conf = null;
//    	if("1".equals(showType))
//    	  conf = sysConfService.findRemark(key);
//    	else
        conf = sysConfService.findVal(key);

      if(StringUtil.isNotEmpty(conf))
    	  pageContext.getOut().print(conf);
      else
        pageContext.getOut().print(sysConfService.findVal(key));

    } catch (IOException e) {
      Logger.e(this.getClass(), "配置标签异常", e);
    }
    return EVAL_BODY_INCLUDE;
  }

  public void setKey(String key) {
    this.key = key;
  }

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}


}
