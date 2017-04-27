package org.tplatform.common;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by tianyi on 2017/4/5.
 */
@Order(2)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
  @Override
  protected boolean enableHttpSessionEventPublisher() {
    return true;
  }
}
