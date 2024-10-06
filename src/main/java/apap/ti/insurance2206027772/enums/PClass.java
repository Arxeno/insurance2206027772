package apap.ti.insurance2206027772.enums;

public enum PClass {
  LEVEL_1,
  LEVEL_2,
  LEVEL_3;

  public Integer getClassLimit(PClass pClass) {
    switch (pClass) {
      case LEVEL_1:
        return 100000000;
      case LEVEL_2:
        return 50000000;
      default:
        return 25000000;
    }
  }
}