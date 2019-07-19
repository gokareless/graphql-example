package org.gokareless.examples.graphql.filters;

public class EscapeUtil {

  public static String escapeHtml(String value) {
    return value.replaceAll("<", "&lt");
  }

}
