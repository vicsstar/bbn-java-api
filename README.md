# BBN SMS Gateway Java SDK for HTTP API (version 1.0)
=====================================================

BBN SMS Gateway Java SDK for HTTP API (version 1.0) is the first Java port to the BBN SMS Gateway HTTP API. Java developers can now interface with the BBN SMS Gateway API with ease - not having to write long lines of code to do same from project to project. Java developers can easily adopt the library into any project that needs to send sms using the BBN SMS Gateway

# Configuration

There's little need for configuration. Most of the common configuration is already provided in bbn/api/sms/bbn.properties; however, there's a small matter of authentication username and password. This can be provided in your own .properties file:

For example, create the file "bbn.properties" in the root of your application's classpath (note: it must be called bbn.properties)
add these lines to the file.

```
username=<your username>
password=<your password>

e.g.
username=user123
password=password123
```

Then your code can go like this:

```
import bbn.api.sms.BbnApi;
...
BbnApi api = new BbnApi();
api.checkBalance();
```

This will check the credit balance for the user whose user details you provided in your config file (bbn.properties)

Another option is to provide the authentication username and password manually for every operation.

e.g.
```
import bbn.api.sms.BbnApi;
...
BbnApi api = new BbnApi();
api.checkBalance("user123", "password123");

This will check the credit balance for the user (user123) whose details was provided.
```

# Example Usage

The source code for how to use the API is available as tests in the tests package; however, here's a quick look at how to use the API.

## Java Developers
```
import bbn.api.sms.BbnApi;
...
SMS sms = new SMS();
sms.setMessage("This is a test message.");
sms.setSender("senderid"); // The sender id can be a mobile number.
sms.setMobile("+2347031234567"); // Check the BBN website for details on the country codes supported and the preferred format.
BbnApi api = BbnApi.getInstance();
Result result = api.sendMessage(sms); // Send the message to the recipient specified as mobile.

// Make sure there was no error message.
if (!result.isError()) {
  // Show message indicating the message was sent successfully.
}
```

# Suitability for Scala Developers

The API is fully usable for Scala Developers without any change to the library itself.

# Contact Me

Victor Igbokwe
Email: <vicsstar@yahoo.com>
Mobile: +2347030541645
Twitter: <http://twitter.com/vicsstar>
Facebook <http://www.facebook.com/vicsstar>

For any suggestions or feedback regarding this library, you can contact me on this address <vicsstar@yahoo.com> or <vicsstar1987@gmail.com>

or

Contact the BBN Place at <http://www.bbnplace.com/?entry=contact>

Thank you.
