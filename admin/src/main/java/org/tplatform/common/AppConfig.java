package org.tplatform.common;

import org.tplatform.config.WebAppConfig;

/**
 * Created by tianyi on 2017/1/19.
 */
public class AppConfig extends WebAppConfig {
  {
    super.setUrlRegex("^/(login|static|api)+.*$");
  }
}
