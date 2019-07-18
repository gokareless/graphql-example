package org.gokareless.examples.graphql;

import java.lang.reflect.Field;
import java.util.Collection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

//@Aspect
//@Configuration
public class EncodingAspect {

  public static final String ENCODING_ATTRIBUTE = "X-Wellsmith-Html-Safe-Strings:";

  @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
  public void encoded() {
  }

  @AfterReturning(pointcut = "encoded()", returning = "value")
  public void after(JoinPoint point, Object value) {
    if (point.getTarget().getClass().isAnnotationPresent(Component.class)) {
      RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
      if (requestAttributes != null && isEncodingEnabled(requestAttributes)) {
        if (value instanceof Collection) {
          Collection collection = (Collection) value;
          for (Object object : collection) {
            processFields(object);
          }
        } else {
          processFields(value);
        }
      }
    }
  }

  private boolean isEncodingEnabled(RequestAttributes requestAttributes) {
    return (boolean) requestAttributes
        .getAttribute(ENCODING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
  }

  private void processFields(Object object) {
    for (Field field : object.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Encoded.class) && field.getType().equals(String.class)) {
        try {
          field.setAccessible(true);
          field.set(object, "Encoded: " + field.get(object));
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
