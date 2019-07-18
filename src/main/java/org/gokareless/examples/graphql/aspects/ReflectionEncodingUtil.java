package org.gokareless.examples.graphql.aspects;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.text.StringEscapeUtils;

class ReflectionEncodingUtil {

  private ReflectionEncodingUtil() {
  }

  static void processObject(Object value) {
    Class<?> type = value.getClass();
    if (type.isInstance(Collection.class)) {
      processCollection((Collection) value);
    } else {
      processFields(value);
    }
  }

  private static void processMap(Map map) {
    processCollection(map.keySet());
    processCollection(map.values());
  }

  private static void processCollection(Collection collection) {
    for (Object object : collection) {
      processFields(object);
    }
  }

  private static void processArray(Object[] array) {
    for (Object object : array) {
      processFields(object);
    }
  }

  private static void processFields(Object object) {
    for (Field field : object.getClass().getDeclaredFields()) {
      try {
        Class<?> type = field.getType();
        if (type.equals(String.class)) {
          encode(object, field);
        } else if (type.isInstance(Collection.class)) {
          processCollection((Collection) field.get(object));
        } else if (type.isInstance(Map.class)) {
          processMap((Map) field.get(object));
        } else if (type.isArray()) {
          processArray((Object[]) field.get(object));
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  private static void encode(Object object, Field field) throws IllegalAccessException {
    if (field.isAnnotationPresent(Encoded.class)) {
      field.setAccessible(true);
      field.set(object,
          StringEscapeUtils.escapeHtml4((String) field.get(object)));
    }
  }
}
