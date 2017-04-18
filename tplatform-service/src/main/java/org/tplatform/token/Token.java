package org.tplatform.token;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Form表单验证
 * Created by Tianyi on 2014/12/10.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {
  // 验证类型
  TokenType[] type() default TokenType.GRAPH_CODE;
  // token key
  String key() default "";
  // store type
  String store() default "session";
}
