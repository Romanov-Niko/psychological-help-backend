package com.kpi.exception;

public class UserWithGivenEmailExistException extends RuntimeException {
  public UserWithGivenEmailExistException(String message) {
    super(message);
  }
}
