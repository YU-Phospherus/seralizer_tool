package cn.jyson;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author nephrite
 */
class SerializerTest {

  @Test
  void toJsonString() throws IllegalAccessException {
    List<ClassRoom> list = new ArrayList<>();
    ClassRoom[] classRooms = new ClassRoom[2];
    ClassRoom cr = new ClassRoom();
    cr.setName("math-20");
    cr.setGraduation(true);
    classRooms[0] = cr;

    Student stu;
    stu = new Student();
    stu.setId(2022);
    stu.setName("tom");
    cr.getStudents().add(stu);

    stu = new Student();
    stu.setId(2021);
    stu.setName("jack");
    cr.getStudents().add(stu);
    list.add(cr);

    cr = new ClassRoom();
    cr.setName("english-20");
    cr.setGraduation(true);
    classRooms[1] = cr;

    stu = new Student();
    stu.setId(2023);
    stu.setName("timi");
    cr.getStudents().add(stu);
    list.add(cr);

    String result = toJson(list);
//    String result_1 = toJson(classRooms);

    assertNotNull(result);
//    assertNotNull(result_1);

    byte[] bytes = result.getBytes();
//    byte[] bytes_1 = result_1.getBytes();

    assertNotNull(bytes);
//    assertNotNull(bytes_1);

    System.out.println(result);
//    System.out.println(result_1);

    OutputStream out = null;
    try {
      out = new FileOutputStream("temp/de.bin");
      out.write(bytes);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public String toJson(Object obj) throws IllegalAccessException {
    if (obj == null) {
      return "";
    }

    if (obj instanceof List) {
      return toJsonArray(obj);
    } else {
      return toJsonObject(obj);
    }

  }

  private String toJsonObject(Object obj) throws IllegalAccessException {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    Class<?> clazz = obj.getClass();
    Field[] fields = clazz.getDeclaredFields();

    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      sb.append("\"");

      String fieldName = fields[i].getName();
      sb.append(fieldName);
      sb.append("\":");

      Object fieldValue = fields[i].get(obj);
      if (fieldValue instanceof Number || fieldValue instanceof Boolean) {
        sb.append(fieldValue);
      } else if (fieldValue instanceof List) {
        String s = toJsonArray(fieldValue);
        sb.append(s);
      } else {
        sb.append("\"");
        sb.append(fieldValue);
        sb.append("\"");
      }

      if (i != fields.length - 1) {
        sb.append(",");
        continue;
      }

      sb.append("}");
    }
    return sb.toString();
  }

  private String toJsonArray(Object obj) throws IllegalAccessException {
    if (obj == null) {
      return "[]";
    }

    StringBuilder sb = new StringBuilder();
    if (obj instanceof List) {
      sb.append("[");
      Object[] objects = ((List<?>) obj).toArray();
      for (Object object : objects) {
        String s;
        if (object instanceof List) {
          s = toJsonArray(object);
        } else {
          s = toJsonObject(object);
        }
        sb.append(s);
        sb.append(",");
      }
      sb.append("]");
    }

    return sb.toString();
  }


}