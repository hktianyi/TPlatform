package org.tplatform.tag;

import org.tplatform.domain.ConfigService;
import org.tplatform.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Tianyi on 2015/7/3.
 */
public class ConfigTag extends TagSupport {

  private static final ConfigService configService = SpringContextUtil.getBean(ConfigService.class);
  private String key;
  private String showType;
  @Override
  public int doStartTag() throws JspException {
//    try {
//    	 String conf = null;
////    	if("1".equals(showType))
////    	  conf = sysConfService.findRemark(key);
////    	else
//        conf = sysConfService.findVal(key);
//
//      if(StringUtil.isNotEmpty(conf))
//    	  pageContext.getOut().print(conf);
//      else
//        pageContext.getOut().print(sysConfService.findVal(key));
//
//    } catch (IOException e) {
//      Logger.e("配置标签异常", e);
//    }
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
