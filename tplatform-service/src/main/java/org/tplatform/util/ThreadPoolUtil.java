package org.tplatform.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 * Created by Tianyi on 2015/6/16.
 */
public class ThreadPoolUtil {

  private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(900);
  private static final ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(10);

  private ThreadPoolUtil() {}

  public static void execute(Runnable runnable) {
    fixedThreadPool.execute(runnable);
  }

  /**
   * 延迟delay时间后执行
   * @param runnable
   */
  public static ScheduledFuture<?> schedule(Runnable runnable, int delay, TimeUnit timeUnit) {
    return schedulePool.schedule(runnable, delay, timeUnit);
  }

  /**
   * 每隔一段时间执行
   * @param runnable
   * @param interval
   * @param timeUnit
   */
  public static ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, int interval, TimeUnit timeUnit) {
    return schedulePool.scheduleAtFixedRate(runnable, interval, interval, timeUnit);
  }

  /**
   * 延迟delay时间后每隔一段时间执行，
   * @param runnable
   * @param delay
   * @param interval
   * @param timeUnit
   */
  public static ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, int delay, int interval, TimeUnit timeUnit) {
    return schedulePool.scheduleAtFixedRate(runnable, delay, interval, timeUnit);
  }

}
