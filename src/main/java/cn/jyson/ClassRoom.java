package cn.jyson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nephrite
 */
public class ClassRoom {

  private String name;

  private boolean isGraduation;

  private List<Student> students = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isGraduation() {
    return isGraduation;
  }

  public void setGraduation(boolean graduation) {
    isGraduation = graduation;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}