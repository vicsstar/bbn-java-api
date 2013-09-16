package bbn.api.sms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 4:28:20 AM
 */
public class SmsGroup implements Serializable {

  private static final long serialVersionUID = 1L;
  private String sender;
  private List<String> mobileList = new ArrayList<>();
  private String message;

  public SmsGroup() {
  }

  /**
   * Construct an SmsGroup with a sender id and message.
   *
   * @param sender The sender id for the SMS.
   * @param message The message to send to the recipient.
   */
  public SmsGroup(String sender, String message) {
    this.sender = sender;
    this.message = message;
  }

  /**
   * Get the mobile number to send the SMS to.
   *
   * @return
   */
  public List<String> getMobileList() {
    return mobileList;
  }

  public String getMobileListCommaSeparated() {
    String mobileText = "";

    for (int i = 0; i < mobileList.size(); i++) {
      mobileText += mobileList.get(i) + (i + 1 == mobileList.size() ? "" : ",");
    }
    return mobileText;
  }

  /**
   * Set the mobile number to send the SMS to.
   *
   * @param mobileList
   */
  public void setMobileList(List<String> mobileList) {
    if (mobileList != null) {
      this.mobileList.addAll(mobileList);
    }
  }

  public void addMobile(final String mobile) {
    if (mobile != null) {
      mobileList.add(mobile);
    }
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "SmsGroup{" + "sender=" + sender + ", message=" + message + '}';
  }
}
