package org.tplatform.framework.util;
//
//import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.Column;
//import org.hibernate.mapping.PersistentClass;
//import org.hibernate.mapping.PrimaryKey;
//import org.hibernate.mapping.Property;
//
//import java.util.Iterator;
//
///**
// * hibernate实体工具
// * Created by Tianyi on 2015/7/3.
// */
//public class EntityUtil {
//
//  private static Configuration hibernateConf;
//
//  private EntityUtil() {
//  }
//
//  private static Configuration getHibernateConf() {
//    if (hibernateConf == null) {
//      hibernateConf = new AnnotationConfiguration().configure();
//      hibernateConf.buildSessionFactory();
//    }
//    return hibernateConf;
//  }
//
//  private static PersistentClass getPersistentClass(Class<?> clazz) {
//    synchronized (EntityUtil.class) {
//      PersistentClass pc = getHibernateConf().getClassMapping(
//          clazz.getName());
//      if (pc == null) {
//        hibernateConf = getHibernateConf().addClass(clazz);//*.hbm.xml方式 注解方式去掉这个
//        pc = getHibernateConf().getClassMapping(clazz.getName());
//
//      }
//      return pc;
//    }
//  }
//
//  /**
//   * 功能描述：获取实体对应的表名
//   *
//   * @param clazz 实体类
//   * @return 表名
//   */
//  public static String getTableName(Class<?> clazz) {
//    return getPersistentClass(clazz).getTable().getName();
//  }
//
//  /**
//   * 获取指定列的列名
//   *
//   * @param clazz
//   * @param num
//   * @return
//   */
//  public static String getColumnName(Class<?> clazz, int num) {
//    return getPersistentClass(clazz).getTable().getColumn(num).getName();
//  }
//
//  /**
//   * 功能描述：获取实体对应表的主键字段名称，只适用于唯一主键的情况
//   *
//   * @param clazz 实体类
//   * @return 主键字段名称
//   */
//  public static String getPrimaryKey(Class<?> clazz) {
//
//    return getPrimaryKeys(clazz).getColumn(0).getName();
//
//  }
//
//  /**
//   * 功能描述：获取实体对应表的主键字段名称
//   *
//   * @param clazz 实体类
//   * @return 主键对象primaryKey ，可用primaryKey.getColumn(i).getName()
//   */
//  public static PrimaryKey getPrimaryKeys(Class<?> clazz) {
//
//    return getPersistentClass(clazz).getTable().getPrimaryKey();
//
//  }
//
//  /**
//   * 功能描述：通过实体类和属性，获取实体类属性对应的表字段名称
//   *
//   * @param clazz        实体类
//   * @param propertyName 属性名称
//   * @return 字段名称
//   */
//  public static String getColumnName(Class<?> clazz, String propertyName) {
//    PersistentClass persistentClass = getPersistentClass(clazz);
//    Property property = persistentClass.getProperty(propertyName);
//    Iterator<?> it = property.getColumnIterator();
//    if (it.hasNext()) {
//      Column column = (Column) it.next();
//      return column.getName();
//    }
//    return null;
//  }
//
//
//}
