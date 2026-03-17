package org.fileorganizerjava.enums;

public enum SubFolderOption {
  PULL_FILES_TO_ROOT(1),
  DO_NOT_PULL_FILES_TO_ROOT(2);

  private final int value;

  SubFolderOption(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
