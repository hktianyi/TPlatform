package org.tplatform.store.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.tplatform.framework.log.Logger;

import java.util.Map;

/**
 * Created by Tianyi on 2014/12/4.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  @Override
  public void setTargetDataSources(Map<Object, Object> targetDataSources) {
    // TODO 获取数据源，设置到targetDataSources中

    super.setTargetDataSources(targetDataSources);
    super.afterPropertiesSet();
  }

  @Override
  protected Object determineCurrentLookupKey() {
    Logger.d("===============================>Spring获取动态路由节点：" + DbContextHolder.getDbType());
    return DbContextHolder.getDbType();
  }
}
