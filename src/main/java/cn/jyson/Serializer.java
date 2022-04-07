package cn.jyson;

import java.lang.reflect.Field;

/**
 * @author nephrite
 */
public class Serializer {

  public static String toJsonString(Object obj, Class<?> clazz) {
    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields) {
    }

    return "";
  }

}