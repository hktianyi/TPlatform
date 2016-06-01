package org.tplatform.framework.util;

import org.tplatform.constant.GlobalConstant;
import org.tplatform.framework.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Tianyi on 2015/4/15.
 */
public class FreeMarkerUtil {
  private static Configuration cfg;

  public FreeMarkerUtil() {
  }

  private static Configuration getCfg() throws IOException {
    if (cfg == null) {
      cfg = new Configuration(Configuration.VERSION_2_3_22);
      cfg.setDirectoryForTemplateLoading(new File(GlobalConstant.PROJECT_ROOT_PATH.concat("WEB-INF/freemarker/")));
      cfg.setDefaultEncoding(GlobalConstant.ENCODE);
      cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      cfg.setClassicCompatible(true);
    }
    return cfg;
  }

  public static String getTemplateStr(String ftlName) {
    return getTemplateStr(ftlName, null);
  }

  public static String getTemplateStr(String ftlName, Object object) {
    if (StringUtil.isEmpty(ftlName)) return null;

    try {
      Template template = getCfg().getTemplate(ftlName, GlobalConstant.ENCODE);
      StringWriter stringWriter = new StringWriter();
      template.process(object, stringWriter);
      return stringWriter.toString();
    } catch (IOException e) {
      Logger.e(FreeMarkerUtil.class, "根据模板名获取模板失败：" + ftlName, e);
    } catch (TemplateException e) {
      Logger.e(FreeMarkerUtil.class, "渲染模板数据失败：" + ftlName, e);
    }
    return null;
  }
}
