package org.tplatform.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Tianyi on 2016/7/13.
 */
public class SessionListener implements HttpSessionListener {
  @Override
  public void sessionCreated(HttpSessionEvent se) {
    se.getSession().setMaxInactiveInterval(7200);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {

  }
}
