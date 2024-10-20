package apap.ti.insurance2206027772.enums;

import java.time.Month;
import java.util.List;

public enum Quarter {
  Q1("Q1"),
  Q2("Q2"),
  Q3("Q3"),
  Q4("Q4");

  private String value;

  private Quarter(String value) {
    this.value = value;
  }

  public static List<Month> getMonths(Quarter quarter) {
    switch (quarter) {
      case Q1:
        return List.of(Month.JANUARY, Month.FEBRUARY, Month.MARCH);
      case Q2:
        return List.of(Month.APRIL, Month.MAY, Month.JUNE);
      case Q3:
        return List.of(Month.JULY, Month.AUGUST, Month.SEPTEMBER);
      default:
        return List.of(Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
    }
  }

  public String getValue() {
    return this.value;
  }
}
