package org.gokareless.examples.graphql;

import graphql.schema.DataFetchingEnvironment;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;

@Aspect
@Configuration
public class EncodingAspect {

//  @Autowired
//  DataFetchingEnvironment environment;

    @AfterReturning(pointcut = "execution(* org.gokareless.examples.graphql.*.*(..))", returning = "value")
    public void after(JoinPoint point, Object value) {
      if (value instanceof Collection) {
        Collection collection = (Collection) value;
        for (Object object : collection) {
          processFields(object);
        }
      } else {
        processFields(value);
      }
    }

  private void processFields(Object object) {
    for (Field field : object.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(Encodable.class) && field.getType().equals(String.class)) {
        try {
          field.set(object, "Encoded: " + field.get(object));
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
