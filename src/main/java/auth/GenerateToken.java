package auth;

import utils.ResourceBundleManager;

import java.util.Base64;
import java.util.ResourceBundle;

public class GenerateToken {

    public static String generate() {

        System.out.println("Generating Basic Authentication Token ...............................");
        // Create a new String variable to store the username and password.
        String username = ResourceBundleManager.getString("username");
        String password = ResourceBundleManager.getString("password");

        // Generate a Base64 encoded string of the username and password.
        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        // Create a new String variable to store the Authorization header value.
        String authorizationHeader = "Basic " + encodedCredentials;

        // Print the Authorization header value to the console.
        System.out.println(authorizationHeader);
        return authorizationHeader;
    }
}