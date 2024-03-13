import auth.GenerateToken;
import dto.LegalEntityDto;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import utils.ResourceBundleManager;
import utils.ResponseParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SAPIntegration {

    public static void main(String[] args) throws IOException {
        // SAP API endpoint URL
        String sapApiUrl = ResourceBundleManager.getString("LegalEntity");
//        System.out.println("sap url string from properties = " + sapApiUrl);

        // construct url with ERP config values
        sapApiUrl = constructApiUrl(sapApiUrl);

        // execute sap api request
        String result = executeAPICall(sapApiUrl);

        // parse result
        ArrayList<LegalEntityDto> legalEntityDtos = ResponseParser.getResult(result);
        System.out.println("Legal entity DTO List = " + legalEntityDtos.toString());

    }

    private static String executeAPICall(String sapApiUrl) {
        // Construct the HTTP request
        HttpGet request = new HttpGet(sapApiUrl);

        // Add headers, authentication, or other necessary configurations to the request
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", GenerateToken.generate());

        try {
            // Create an HTTP client
            HttpClient httpClient = HttpClients.createDefault();

            // Execute the request
            System.out.println("Executing http request ..............................");
            HttpResponse response = httpClient.execute(request);

            // Process the response
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Read and process the response content
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                System.out.println("SAP API Response: " + result.toString());
                return result.toString();

            } else {
                System.err.println("Error: SAP API request failed with status code " + statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String constructApiUrl(String sapApiUrl) {
        //Build the url using all configuration options
        Map<String, String> parameters = new HashMap<>();
        parameters.put("top", URLEncoder.encode(ResourceBundleManager.getString("top"), StandardCharsets.UTF_8));
        parameters.put("orderBy", URLEncoder.encode(ResourceBundleManager.getString("orderBy"), StandardCharsets.UTF_8));
        parameters.put("filter", URLEncoder.encode(ResourceBundleManager.getString("filter"), StandardCharsets.UTF_8));
        parameters.put("select", URLEncoder.encode(ResourceBundleManager.getString("select"), StandardCharsets.UTF_8));

        System.out.println("ERP Config Parameters = " + parameters.toString());

        StringSubstitutor stringSubstitutor = new StringSubstitutor(parameters);
        sapApiUrl = stringSubstitutor.replace(sapApiUrl);
        System.out.println("Constructed SAP url = " + sapApiUrl);
        return sapApiUrl;
    }
}
