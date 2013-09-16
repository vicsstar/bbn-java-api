package bbn.api.sms.domain;

import java.io.Serializable;

/**
 * Holds the result of an HTTP request on the BBN network.
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 3:59:47 AM
 */
public class Result<T> implements Serializable {

  public static final long serialVersionUID = 1L;
  private boolean error = true;
  private int code;
  private String message;
  private T value;

  public Result() {
  }

  /**
   * Construct a new result.
   *
   * @param value The value of the result, if any.
   */
  public Result(T value) {
    this.value = value;
  }

  /**
   * Construct a new result.
   *
   * @param message The message from the result, if any.
   * @param value The value of the result, if any.
   */
  public Result(String message, T value) {
    this.message = message;
    this.value = value;
  }

  /**
   * Get the message for the result.
   *
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   * Set the message for this result, if any.
   *
   * @param message The message to set.
   * @return Result an instance containing details of the result.
   */
  public Result setMessage(String message) {
    this.message = message;
    return this;
  }

  /**
   * Returns the code for the result, if any.
   *
   * @return
   */
  public int getCode() {
    return code;
  }

  /**
   * Set the response code for the result, if any.
   *
   * @param code The code to set.
   * @return Result an instance containing details of the result.
   */
  public Result setCode(int code) {
    this.code = code;
    return this;
  }

  /**
   * Returns the value for the result, if any.
   *
   * @return
   */
  public T getValue() {
    return value;
  }

  /**
   * Set the actual value of the result, if any.
   *
   * @param value The value to set.
   * @return Result an instance containing details of the result.
   */
  public Result setValue(T value) {
    this.value = value;
    return this;
  }

  /**
   * Returns if an error occurred.
   *
   * @return
   */
  public boolean isError() {
    return error;
  }

  /**
   * Set if an error occurred.
   *
   * @param error Boolean indicating an error occurred or not.
   * @return Result an instance containing details of the result.
   */
  public Result setError(boolean error) {
    this.error = error;
    return this;
  }

  @Override
  public String toString() {
    return "Result{" + "error=" + error + ", code=" + code + ", message=" + message + ", value=" + value + '}';
  }
}
