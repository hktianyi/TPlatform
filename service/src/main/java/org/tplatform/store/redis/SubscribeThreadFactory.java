package org.tplatform.store.redis;

import org.tplatform.framework.log.Logger;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tianyi on 2015/1/7.
 */
public class SubscribeThreadFactory implements ThreadFactory {
  // members
  static final AtomicInteger poolCounter = new AtomicInteger(1);
  final AtomicLong threadCounter = new AtomicLong(1);
  final ThreadGroup group;
  final String namingPattern;
  // static block

  // constructors
  public SubscribeThreadFactory() {
    SecurityManager securityManager = System.getSecurityManager();
    group = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
    namingPattern = "redis-" + poolCounter.getAndIncrement() + "-subscribe-thread-";
  }
  // properties

  // public methods

  @Override
  public Thread newThread(Runnable r) {
    Thread worker = new Thread(group, r, namingPattern + threadCounter.getAndIncrement(), 0);
    worker.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        Logger.e(SubscribeThreadFactory.class, e.getMessage());
      }
    });
    if (worker.isDaemon()) {
      worker.setDaemon(false);
    }
    if (worker.getPriority() != Thread.NORM_PRIORITY) {
      worker.setPriority(Thread.NORM_PRIORITY);
    }
    return worker;
  }

}
