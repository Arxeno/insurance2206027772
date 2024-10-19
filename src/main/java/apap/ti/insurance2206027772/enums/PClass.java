package apap.ti.insurance2206027772.enums;

public enum PClass {
  LEVEL_1(1),
  LEVEL_2(2),
  LEVEL_3(3);

  private final int value;

  private PClass(int value) {
    this.value = value;
  }

  public static Long getClassLimit(PClass pClass) {
    switch (pClass) {
      case LEVEL_1:
        return 100000000L;
      case LEVEL_2:
        return 50000000L;
      default:
        return 25000000L;
    }
  }

  private Long getClassLimit(int value) {
    switch (value) {
      case 1:
        return 100000000L;
      case 2:
        return 50000000L;
      default:
        return 25000000L;
    }
  }

  public int getValue() {
    return this.value;
  }

  public String toString() {
    String formatted = String.format("%,d", getClassLimit(value));

    return String.format("Class %d - Limit: IDR %s.00", value, formatted);
  }
}
