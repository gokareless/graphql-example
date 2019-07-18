package org.gokareless.examples.graphql.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@Aspect
//@Configuration
public class EncodingAspect {

  private static final String ENCODING_HEADER = "X-Wellsmith-Html-Safe-Strings:";

  @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
  public void encoded() {}

  @AfterReturning(pointcut = "encoded()", returning = "value")
  public void after(JoinPoint point, Object value) {
    if (value != null && point.getTarget().getClass().isAnnotationPresent(Component.class)) {
      // TODO: Uncomment when header-based processing implemented
       boolean encodingEnabled = isEncodingEnabled();
      if (encodingEnabled) {
        ReflectionEncodingUtil.processObject(value);
      }
    }
  }

  private boolean isEncodingEnabled() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      return Boolean.valueOf(((ServletRequestAttributes) requestAttributes)
          .getRequest()
          .getHeader(ENCODING_HEADER));
    }
    return false;
  }
}
