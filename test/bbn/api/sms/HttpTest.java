package bbn.api.sms;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 2:53:57 AM
 */
public class HttpTest {

  private Http http;

  public HttpTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    http = new Http("http://www.bbnplace.com");
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of addParam method, of class Http.
   */
  @Test
  public void testAddParam() {
    System.out.println("addParam");
    String name = "key1";
    String value = "value1";
    http.addParam(name, value);
  }

  /**
   * Test of addParams method, of class Http.
   */
  @Test
  public void testAddParams() {
    System.out.println("addParams");
    Map<String, String> params = new HashMap<>();
    params.put("key2", "value2");
    params.put("key3", "value3");
    params.put("key4", "value4");
    http.addParams(params);
  }

  @Test
  public void testRemoveParam() {
    System.out.println("Remove param");
    http.removeParam("key3");
  }

  /**
   * Test of get method, of class Http.
   */
  @Test
  public void testGet() throws Exception {
    System.out.println("get");
    String result = http.get();
    assertNotNull(result);
  }

  /**
   * Test of post method, of class Http.
   */
  @Test
  public void testPost() throws Exception {
    System.out.println("post");
    String result = http.post();
    assertNotNull(result);
  }

  @Test
  public void testGetParam() {
    System.out.println("Get param");
    http.addParam("key1", "value1");
    http.addParam("key4", "value4");
    String result = http.getParam("key3");
    assertNull(result);
    assertNotNull(http.getParam("key1"));
    assertEquals("value4", http.getParam("key4"));
  }
}