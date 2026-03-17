package org.fileorganizerjava.enums;

public enum GroupByOption {
  FILE_CATEGORY("category"),
  FILE_CATEGORY_AND_FILE_TYPE("category-and-type"),
  FILE_TYPE("type");

  private final String value;

  GroupByOption(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
