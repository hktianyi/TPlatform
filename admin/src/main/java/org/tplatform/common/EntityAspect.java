package org.tplatform.common;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by tianyi on 16/3/22.
 */
@Configuration
@EnableAspectJAutoProxy
@Component
@Aspect
public class EntityAspect {

//  @Pointcut("execution(* com..service..save(..))")
//  public void save() {}
//  @Pointcut("execution(* com..service..update(..))")
//  public void update() {}
//
//  @Before("save(..)")
//  public void beforeSave(JoinPoint joinPoint) {
//    System.out.println("==============111" + joinPoint);
//  }
//  @Before("update(..)")
//  public void beforeUpdate(JoinPoint joinPoint) {
//    System.out.println("==============222" + joinPoint);
//  }
}
