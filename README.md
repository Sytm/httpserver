# httpserver
This is a small libary for a HTTP WebServer

As first, you must create a new instance of an WebListener like this:
```java
public class Listener implements WebListener {

    @Override
    public Response recieve(RequestData requestData) {
        // Get the requested path on the webserver | http://www.examle.com/PATH
        String requestedPath = requestData.getPath();
        // Get the type of the request. Aviable: GET and POST
        RequestType reqestType = requestData.getType();
        // Get the post body or the data from the url | Place of data http://example.com/index.html?DATA
        // Structure of data http://example.com/index.html?key=value&keytwo=valuetwo
        Map<String, String> data = requestData.getData();
        // Get the InetAddress of the requester
        InetAddress address = requestData.getAddress();
        
        // Now: Make a new instance of Response with
        Response response = Response.newResponse(true);
        // The true adds the default header "Content-Type: text/html"
        // Add your own headers
        response.getHeaders().put("Content-Type", "text/plain");
        // Set the body of your page
        response.setContent("This is the example content of your webpage");
        // Sets the response code for your webpage. Default is HTTPResponseCode.FINE
		response.setResponseCode(HTTPResponseCode.FORBIDDEN);
		// If you want to send the client raw binary, do this:
		byte[] binary = methodToGetTheBinary();
		// Creating an empty attachment
		Attachment attachment = Attachment.createAttachment();
		// Setting the content of the attachment
		attachment.setContent(binary);
		// Setting the attachment of the response
		response.addAttachment(attachment);
		// Note: When a attachment is set to an response, the original content is ignored!
		
		// Or simply use predefined responses
		return Response.NOT_FOUND;
    }
}
```
Or use a predefinded Listener class:<br>
But I do NOT recommend this! PhP Scripts on your server won't be processed and will displayed as a normal text-file!<br>
This libary was made for websites, which are in java programmed!<br>
```java
WebListener listener = new FileListener(new File("path/to/your/root/directory/"));
```
<br><br>
Now we create our WebServer instance:
```java
public static void main(String[]) {
    // Creating a new instance of ServerProperites
    ServerProperties props = ServerProperties.createDefault();
    // Setting the port
    props.setPort(9000);
    // Setting the amount of worker threads 
    properties.setWorkerThreads(5);
    WebListener listener = new Listener();
    // Setting the Listener
    properties.setListener(listener);
    // If you want, you can set an AccessFilter, where you can control, if the ip have access to this, or not. <br>
    // Good for antispam, white- and blacklist
    properties.setAcessFilter(yourCustomFilter);
    // The first argument is the port, which the server is running on. The second one is the amount
    // of threads, which are processing the requests (Notice: The amount of threads is plus one, because the
    // WebServer class has it's own thread). And the last parameter is our listener.
    WebServer server = new WebServer(9000, 10, listener);
    // Now we can simply start the server with
    server.start();
}
```
Now open your browser, type localhost:9000 or your custom port, or if the webserver is on an other server/computer enter the domain or the ip and then the port. Now you'll see the webpage, from above!<br>
To stop the server do this:
```java
    server.shutdown();
    // Done!
```

If you have found a bug, or have some nice improvements. Simply clone this, edit it and make a pull request 
Thanks!
