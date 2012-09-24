package bbn.api.sms;

/**
 * A class that defines all the basic options for the BBN SMS API.
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 1:15:02 AM
 */
public final class BBN {

  public static final String DEFAULT_CONFIG_FILE = "bbn/api/sms/bbn-default.properties";
  public static final String USER_CONFIG_FILE = "bbn.properties";

  public static class Resource {

    private static String mainUrl() {
      // retrieve the default sms api.
      BbnApi api = BbnApi.defaultInstance();
      // Construct the request URL.
      final String requestUrl = (api.getConfigAsBoolean("bbn.api.secure")
              ? api.getConfig("bbn.request.uri.secure")
              : api.getConfig("bbn.request.uri.basic"));
      return requestUrl;
    }

    public static String AUTH_URL() {
      // retrieve the default sms api.
      BbnApi api = BbnApi.defaultInstance();

      if (api != null) {
        // Construct the request URL.
        final String requestUrl = mainUrl()
                + api.getConfig("bbn.resource.uri.auth");
        return requestUrl;
      } else {
        return null;
      }
    }

    public static String ACCOUNT_BALANCE_URL() {
      // retrieve the default sms api.
      BbnApi api = BbnApi.defaultInstance();

      if (api != null) {
        // Construct the request URL.
        final String requestUrl = mainUrl()
                + api.getConfig("bbn.resource.uri.acctbals");
        return requestUrl;
      } else {
        return null;
      }
    }

    public static String SEND_SMS_URL() {
      // retrieve the default sms api.
      BbnApi api = BbnApi.defaultInstance();

      if (api != null) {
        // Construct the request URL.
        final String requestUrl = mainUrl()
                + api.getConfig("bbn.resource.uri.send_sms");
        return requestUrl;
      } else {
        return null;
      }
    }

    public static String SCHEDULE_SMS_URL() {
      // retrieve the default sms api.
      BbnApi api = BbnApi.defaultInstance();

      if (api != null) {
        // Construct the request URL.
        final String requestUrl = mainUrl()
                + api.getConfig("bbn.resource.uri.schedule_sms");
        return requestUrl;
      } else {
        return null;
      }
    }
  }

  /**
   * Defines form parameter names allowed by the BBN API.
   */
  public static class Params {

    /**
     * Your BBN username, usually an email.
     *
     * @required true
     */
    public static final String USERNAME = "username";
    /**
     * Your BBN alphanumeric password.
     *
     * @required true
     */
    public static final String PASSWORD = "password";
    /**
     * A 14 digit number or 11 character alphanumeric text.
     *
     * @required true
     */
    public static final String SENDER = "sender";
    /**
     * The content of the text which is targeted at the recipient.
     *
     * @required true
     */
    public static final String MESSAGE = "message";
    /**
     * The mobile phone numbers of the recipients. Multiple phone numbers are
     * separated with comma. Each number must be prefixed with the country code.
     *
     * @required true
     */
    public static final String MOBILE = "mobile";
    /**
     * Default 0. Set to 1 if you want the message to pop on recipient's mobile
     * instead of delivering in their inbox.
     *
     * @required false
     */
    public static final String FLASH = "flash";
    /**
     * Default 0. Set to 1 if you want the message to be scheduled for later
     * delivery.
     *
     * @required false
     */
    public static final String SCHEDULE = "schedule";
    /**
     * Used together with schedule, is the UNIX timestamp value of the date-time
     * when message should be broadcasted. The default time-zone is WAT (GMT
     * +1).
     * <code>Note: if schedule is not set to 1 when using broadcast_time, system will return an error code.</code>
     *
     * @required false
     */
    public static final String BROADCAST_TIME = "broadcast_time";
    /**
     * A 16 character alphanumeric name that will be used to uniquely identify
     * the schedule.
     *
     * @required false
     */
    public static final String SCHEDULE_NAME = "schedule_name";
    /**
     * Default 0. Set to 1 if you want to receive SMS notification when
     * scheduled message has been broadcast.
     *
     * @required false
     */
    public static final String SCHEDULE_NOTIFY = "schedule_notification";
  }

  /**
   * Defines a response returned by the BBN API based on the code and
   * appropriate message.
   */
  public static class Response {
    // the code for the response.

    private final int code;
    // the message for the response.
    private final String message;

    private Response(int code, String message) {
      this.code = code;
      this.message = message;
    }

    /**
     * Returns the code represented by this response.
     *
     * @return code
     */
    public int getCode() {
      return code;
    }

    /**
     * Returns the message represented by this response.
     *
     * @return message
     */
    public String getMessage() {
      return message;
    }
  }

  /**
   * Defines constants representing response codes returned by the BBN API.
   */
  public static class ResponseCode {

    public static final Response REQUEST_TIMEOUT = new Response(1800, "Request timeout.");
    public static final Response MESSAGE_SENT = new Response(1801, "Message successfully sent.");
    public static final Response INVALID_USERNAME = new Response(1802, "Invalid username.");
    public static final Response INCORRECT_PASSWORD = new Response(1803, "Incorrect password.");
    public static final Response INSUFFICIENT_CREDIT = new Response(1804, "Insufficient credit.");
    public static final Response INVALID_URL_SUBMISSION = new Response(1805, "Invalid URL submission.");
    public static final Response INVALID_MOBILE = new Response(1806, "Invalid mobile.");
    public static final Response INVALID_SENDER_ID = new Response(1807, "Invalid sender id.");
    public static final Response MESSAGE_TOO_LONG = new Response(1808, "Message too long.");
    public static final Response EMPTY_MESSAGE = new Response(1809, "Empty message.");
    public static final Response SCHEDULE_SAVED = new Response(1901, "Schedule was successfully saved.");
    public static final Response SCHEDULE_ = new Response(1902, "Scheduled broadcast time cannot be earlier than current time..");
    public static final Response INVALID_BROADCAST_TIME = new Response(1903, "Invalid broadcast time.");
    public static final Response SCHEDULE_NAME_TOO_LONG = new Response(1904, "Schedule name is too long. Maximum of 16 alphanumeric characters allowed.");
    public static final Response INVALID_NOTIFY_ME_VALUE = new Response(1905, "Invalid value for notify me. 0 or 1 expected.");
    public static final Response INCORRECT_SCHEDULE_NAME = new Response(1906, "Incorrect schedule name. Schedule name may contain special character.");
  }
}
