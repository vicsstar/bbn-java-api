package bbn.api.sms;

import bbn.api.sms.utils.URLComposer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Makes HTTP requests on behalf of the BBN SMS API.
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 1:53:11 AM
 */
public class Http {

  /**
   * The HTTP request URL.
   */
  private final String requestUrl;
  /**
   * The HTTP parameters to be used when making HTTP request.
   */
  private Map<String, String> params = new HashMap<String, String>();

  /**
   * Initializes an Http object instance with the request URL.
   *
   * @param requestUrl
   */
  public Http(String requestUrl) {
    this.requestUrl = requestUrl;
  }

  /**
   * Adds an HTTP request parameter to the list of parameters.
   *
   * @param name The name of the request parameter.
   * @param value The associated value of the request parameter.
   */
  public void addParam(final String name, final String value) {
    if (name != null) {
      params.put(name, value);
    }
  }

  /**
   * Adds a series of parameters a once to the parameter list.
   *
   * @param params
   */
  public void addParams(final Map<String, String> params) {
    if (params != null) {
      this.params.putAll(params);
    }
  }

  /**
   * Gets a request parameter value from the Http request object.
   *
   * @param name The name of the parameter to get.
   * @return value The value of the parameter.
   */
  public String getParam(String name) {
    if (name != null) {
      return params.get(name);
    }
    return null;
  }

  /**
   * Removes a query parameter from the Http request object.
   *
   * @param name The name of the Http request parameter to remove.
   */
  public void removeParam(final String name) {
    if (name != null) {
      params.remove(name);
    }
  }

  /**
   * Performs a HTTP GET request.
   *
   * @return the response from the request.
   * @throws MalformedURLException
   * @throws IOException
   */
  public String get() throws MalformedURLException, IOException {
    // Construct a URLComposer to properly compose the get URL.
    URLComposer urlComposer = new URLComposer(requestUrl);
    urlComposer.addParams(params);

    // Make a request to the request URL.
    URL url = new URL(urlComposer.getFullURL());
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    // Stores the content of the HTTP response.
    StringWriter result = null;
    // Writes the result.
    PrintWriter writer = null;
    // Reader to read the content of the HTTP response.
    BufferedReader reader = null;

    try {
      // Initialize the reader.
      reader = new BufferedReader(
              new InputStreamReader(
              connection.getInputStream()));
      // Represents each line in the stream.
      String line;

      // Reads each line from the server.
      while ((line = reader.readLine()) != null) {

        if (writer == null) {
          // Initialize the writer.
          writer = new PrintWriter(result = new StringWriter());
        }
        writer.println(line);
      }
    } finally {

      // close the streams.
      if (reader != null) {
        reader.close();
      }

      if (writer != null) {
        writer.close();
      }
    }

    // return the content of the HTTP response, if any.
    return result.getBuffer().toString();
  }

  /**
   * Performs an HTTP POST request.
   *
   * @return the response from the request.
   * @throws MalformedURLException
   * @throws IOException
   */
  public String post() throws MalformedURLException, IOException {
    // Make a request to the request URL.
    URL url = new URL(new URLComposer(requestUrl).getURLPath());
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // Change the request method.
    connection.setRequestMethod("POST");
    connection.setRequestProperty(
            "Content-type",
            "application/x-www-form-urlencoded");
    // Enable writing to the URL resource.
    connection.setDoOutput(true);
    connection.setDoInput(true);

    PrintWriter requestWriter = new PrintWriter(
            new OutputStreamWriter(connection.getOutputStream(), "8859_1"), true);
    requestWriter.print(
            new URLComposer(requestUrl).addParams(params).requestEncodedParams());
    requestWriter.flush();

    // Stores the content of the HTTP response.
    StringWriter result = new StringWriter();
    // Writes the result.
    PrintWriter writer = null;
    // Reader to read the content of the HTTP response.
    BufferedReader reader = null;

    try {
      // Initialize the reader.
      reader = new BufferedReader(
              new InputStreamReader(
              connection.getInputStream()));
      // Represents each line in the stream.
      String line;

      // Reads each line from the server.
      while ((line = reader.readLine()) != null) {

        if (writer == null) {
          // Initialize the writer.
          writer = new PrintWriter(result);
        }
        writer.println(line);
      }

      if (writer != null) {
        writer.flush();
      }
    } finally {

      // close the streams.
      if (reader != null) {
        reader.close();
      }

      if (writer != null) {
        writer.close();
      }
    }

    // return the content of the HTTP response, if any.
    return result.getBuffer().toString();
  }
}
