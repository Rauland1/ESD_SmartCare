/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Raul
 */
public class PlaceAPI {
    
    // HTTP connection
    public static HttpURLConnection httpConn;
    
    private final String postcode;
    
    public PlaceAPI(String postcode){
        this.postcode = postcode;
    }
    
    // Returns XML string of address
    public String getAddressXML(){
        
        // Strings to use for connection
        String mapsURL = "https://maps.googleapis.com/maps/api/place/autocomplete/xml?input=";
        String apiKey = "AIzaSyCN6-GuxXdTsTRAN7UxigbwRbpE3z5pajA";
        
        String fullURL = mapsURL + postcode.replace(" ", "") + "&types=(regions)&key=" + apiKey;
        
        // Variables for processing and reading the response
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        
        // Try to connect
        try {
            // Create connection
            URL url = new URL(fullURL);
            httpConn = (HttpURLConnection) url.openConnection();
            // Set request method and timeout
            httpConn.setRequestMethod("GET");
            httpConn.setConnectTimeout(5000);
            httpConn.setReadTimeout(5000);
            // Get connection status
            int status = httpConn.getResponseCode();
            
            // Debugging connection
            //System.out.println("Connection status: " + status);
            
            // Check if status is good
            if(status != 200){
                // Read error message
                reader = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
                // Build response content
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                // Close the reader
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                // Build response content
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                // Close the reader
                reader.close();
            }
            
            // Debugging content
            //System.out.println(responseContent.toString());
            
            // Return the parse reponse - full address
            return parseXMLFile(responseContent.toString());
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpConn.disconnect();
        }
        
    }
    
    // Parse XML string and get the desired data
    private String parseXMLFile(String in) {
        StringBuilder address = new StringBuilder();
        
        try {
            // Create document from input string and parse it
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            Document document = db.parse(is);
            
            // Normalize document ???
            document.getDocumentElement().normalize();
            
            // Get root node
            // Element root = document.getDocumentElement();
            
            // Elements that need to be checked to list
            NodeList nList = document.getElementsByTagName("term");
            
            // Iterate through the element list
            for(int temp = 0; temp < nList.getLength(); temp++){
                Node node = nList.item(temp);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    // Get the node and print out the value
                    Element tElement = (Element) node;
                    address.append(tElement.getElementsByTagName("value").item(0).getTextContent());
                    if(temp == nList.getLength() - 1){
                        //do nothing - don't add comma and space after the last term
                    } else {
                        address.append(", "); // otherwise, add comma and space
                    }
                    
                    //System.out.println("Term: " + tElement.getElementsByTagName("value").item(0).getTextContent());                
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        
        return address.toString();
    }
}
