//package org.tplatform.domain;
//
//import org.tplatform.common.BaseRepoImpl;
//
///**
// * Created by Tianyi on 2016/3/18.
// */
//public class ConfigServiceImpl extends BaseRepoImpl<Config> {
//
//  /**
//   * 根据key查询配置
//   *
//   * @param confKey 配置key
//   * @return
//   */
//  public String getByKey(String confKey) {
//    Object result = em.createNativeQuery("SELECT val FROM sys_conf WHERE confKey = ?1").setParameter(1, confKey).getSingleResult();
//    return result != null ? result.toString() : "";
//  }
//}
