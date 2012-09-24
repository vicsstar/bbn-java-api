package bbn.api.sms.domain;

import java.io.Serializable;

/**
 * Defines an SMS to be sent using the BBN API.
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 1:44:21 AM
 */
public class SMS implements Serializable {

  private static final long serialVersionUID = 1L;
  private String sender;
  private String mobile;
  private String message;

  public SMS() {
  }

  /**
   * Construct an SMS with the specified sender id, mobile number and message.
   *
   * @param sender The sender id for the SMS.
   * @param mobile The mobile number to send the SMS to.
   * @param message The message to send to the recipient.
   */
  public SMS(String sender, String mobile, String message) {
    this.sender = sender;
    this.mobile = mobile;
    this.message = message;
  }

  /**
   * Get the sender of the SMS.
   *
   * @return
   */
  public String getSender() {
    return sender;
  }

  /**
   * Set the sender of the SMS.
   *
   * @param sender
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  /**
   * Get the message for the SMS.
   *
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   * Set the message for the SMS.
   *
   * @param message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "SMS{" + "sender=" + sender + ", mobile=" + mobile + ", message=" + message + '}';
  }
}
