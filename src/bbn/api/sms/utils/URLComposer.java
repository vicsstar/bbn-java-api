package bbn.api.sms.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Composes URLs by adding query parameters and anchors.
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 2:05:33 AM
 */
public class URLComposer {

  private String protocol;
  private String path;
  private String url;
  private Map<String, Object> queryParams = new HashMap<String, Object>();

  public URLComposer(String url) {

    if (url == null) {
      throw new NullPointerException("url cannot be null.");
    }
    this.url = url;
    init();
  }

  private void init() {

    try {
      URL u = new URL(url);
      protocol = u.getProtocol();
      protocol = (protocol == null) ? "" : protocol + "://";
      path = u.getPath();
      path = (path == null) ? "" : path;
      url = u.getHost();
      String query = u.getQuery();

      if (query != null) {
        String[] queries = query.split("&");

        for (String q : queries) {
          String[] qus = q.split("=");

          if (qus.length >= 2) {
            addParam(qus[0], qus[1]);
          }
        }
      }
    } catch (IOException e) {
      Logger.getLogger(URLComposer.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  public URLComposer addParam(String key, Object value) {

    // Make sure the key is not null.
    if (key != null) {
      // Check if this key already contains this key.
      Object oldValue = queryParams.get(key);

      if (oldValue == null) {
        queryParams.put(key, value);
      } else {

        if (oldValue instanceof List) {
          List<Object> valueList = (List) oldValue;
          valueList.add(value);
        } else {
          List valueList = new ArrayList();
          valueList.add(oldValue);
          valueList.add(value);
          queryParams.put(key, valueList);
        }
      }
    }
    return this;
  }

  /**
   * Adds a list of a request parameters to the URLComposer.
   *
   * @param params
   */
  public URLComposer addParams(Map params) {
    if (params != null) {
      for (Object key : params.keySet()) {
        addParam(key.toString(), params.get(key));
      }
    }
    return this;
  }

  /**
   * Checks if the this URL contains a query parameter with the specified key.
   *
   * @param key
   * @return
   */
  public boolean containsParam(String key) {
    return queryParams.containsKey(key);
  }

  /**
   * Removes a query parameter from this URL. TODO: implement query parameter
   * removal.
   *
   * @param key
   */
  public void removeParam(String key) {
  }

  /**
   * Returns only the URL of the request without the query parameters.
   *
   * @return
   */
  public String getURLPath() {
    StringBuilder builder = new StringBuilder();
    builder.append(protocol).append(url).append(path);
    return builder.toString();
  }

  /**
   * Composes the full URL by combining URL and query parameters.
   *
   * @return
   */
  public String getFullURL() {
    StringBuilder builder = new StringBuilder();
    builder.append(protocol).append(url).append(path).append("?");
    // Retrieve the parameter keys.
    Set<String> keys = queryParams.keySet();

    for (String k : keys) {
      Object value = queryParams.get(k);

      if (value instanceof List) {
        List valueList = (List) value;

        for (Object v : valueList) {

          try {
            builder.append(k)
                    .append("=")
                    .append(URLEncoder.encode(String.valueOf(v),
                    "UTF-8")).append("&");
          } catch (UnsupportedEncodingException e) {
            Logger.getLogger(URLComposer.class.getName()).log(Level.SEVERE, null, e);
          }
        }
      } else {

        try {
          builder.append(k)
                  .append("=")
                  .append(URLEncoder.encode(String.valueOf(value),
                  "UTF-8")).append("&");
        } catch (UnsupportedEncodingException e) {
          Logger.getLogger(URLComposer.class.getName()).log(Level.SEVERE, null, e);
        }
      }
    }
    String fullUrl = builder.toString();

    if (fullUrl.endsWith("&")) {
      fullUrl = fullUrl.substring(0, fullUrl.lastIndexOf("&"));
    }
    return fullUrl;
  }

  /**
   * Returns the request parameters as a request encoded string.
   *
   * @return
   */
  public String requestEncodedParams() {
    // Construct a string builder to hold the encoded string.
    StringBuilder builder = new StringBuilder();
    // Retrieve the parameter keys.
    Set<String> keys = queryParams.keySet();

    for (String k : keys) {
      Object value = queryParams.get(k);

      if (value instanceof List) {
        List valueList = (List) value;

        for (Object v : valueList) {

          try {
            builder.append(URLEncoder.encode(k, "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(String.valueOf(v),
                    "UTF-8")).append("&");
          } catch (UnsupportedEncodingException e) {
            Logger.getLogger(URLComposer.class.getName()).log(Level.SEVERE, null, e);
          }
        }
      } else {

        try {
          builder.append(URLEncoder.encode(k, "UTF-8"))
                  .append("=")
                  .append(URLEncoder.encode(String.valueOf(value),
                  "UTF-8")).append("&");
        } catch (UnsupportedEncodingException e) {
          Logger.getLogger(URLComposer.class.getName()).log(Level.SEVERE, null, e);
        }
      }
    }

    String encodedString = builder.toString();
    // Remove any trailing '&' character.
    if (encodedString.endsWith("&")) {
      encodedString = encodedString.substring(0, encodedString.lastIndexOf("&"));
    }
    return encodedString;
  }
}
