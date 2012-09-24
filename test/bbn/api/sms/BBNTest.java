package bbn.api.sms;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Igbokwe (vicsstar@yahoo.com)
 * @created Sep 23, 2012 at 2:53:29 AM
 */
public class BBNTest {

  public BBNTest() {
  }

  @BeforeClass
  public static void setUpClass() throws IOException {
    BbnApi.getInstance().setSecure(true);
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testAccountBalanceURLNotNull() {
    assertNotNull(BBN.Resource.ACCOUNT_BALANCE_URL());
  }

  @Test
  public void testSecureURL() {
    assertTrue("https://www.bbnplace.com/sms/bulksms/acctbals.php".equals(
            BBN.Resource.ACCOUNT_BALANCE_URL()));
  }

  @Test
  public void testBasicURL() {
    BbnApi.defaultInstance().setSecure(false);
    assertTrue("http://sms.bbnplace.com/bulksms/acctbals.php".equals(
            BBN.Resource.ACCOUNT_BALANCE_URL()));
  }
}