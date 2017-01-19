package org.tplatform.store.datasource;

/**
 * Created by Tianyi on 2014/12/4.
 */
public class DbContextHolder {
  //利用ThreadLocal解决线程安全问题
  private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

  //获取数据源
  public static DbType getDbType() {
    return contextHolder.get();
  }

  //设置数据源
  public static void setDbType(DbType dbType) {
    contextHolder.set(dbType);
  }

  //清空
  public static void clearDbType() {
    contextHolder.remove();
  }
}
